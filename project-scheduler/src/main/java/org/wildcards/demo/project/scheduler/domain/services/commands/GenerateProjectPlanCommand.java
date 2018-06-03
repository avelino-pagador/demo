package org.wildcards.demo.project.scheduler.domain.services.commands;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wildcards.demo.project.scheduler.domain.entities.Project;
import org.wildcards.demo.project.scheduler.domain.entities.Task;
import org.wildcards.demo.project.scheduler.domain.repositories.ProjectRepository;
import org.wildcards.demo.project.scheduler.domain.services.utils.TaskTraversalUtility;
import org.wildcards.demo.project.scheduler.domain.services.utils.WorkingDaysCalculator;

/**
 * 
 * @author user
 *
 */
@Component
public class GenerateProjectPlanCommand implements Command {

  /**
   * 
   */
  private final ProjectRepository projectRepository;
  
  /**
   * 
   */
  private final WorkingDaysCalculator workingDaysCalculator;
  
  /**
   * 
   * @param projectRepository
   */
  @Autowired
  public GenerateProjectPlanCommand(
      ProjectRepository projectRepository,
      WorkingDaysCalculator workingDaysCalculator) {
    this.projectRepository = projectRepository;
    this.workingDaysCalculator = workingDaysCalculator;
  }
  
  /**
   * 
   */
  @Override
  public void execute(Project project) {
    
    List<Task> tasks = project.getTasks();
    
    Task rootTask = TaskTraversalUtility.getRootTask(tasks);
    
    rootTask.setIndex(1);
    rootTask.setStartDate(workingDaysCalculator.add(new Date(), 1L));
    rootTask.setEndDate(workingDaysCalculator.add(
        rootTask.getStartDate(), 
        rootTask.getDuration()));
    
    Set<Task> taskList = new HashSet<Task>();
    traverse(taskList, rootTask, tasks);
    
    taskList.forEach( task -> {
      if (1 < task.getDependencies().size()) {
        task.setStartDate(getMaxEndDate(task.getStartDate(), task.getDependencies()));
        task.setEndDate(workingDaysCalculator.add(task.getStartDate(), task.getDuration()));
      }
    });
    
    projectRepository.save(project);
  }

  /**
   * 
   * @param dependencies
   * @return
   */
  private Date getMaxEndDate(Date startDate, List<Task> dependencies) {
    Date maxEndDate = startDate;
    for (Task task : dependencies) {
      maxEndDate = workingDaysCalculator.maxDate(maxEndDate, task.getEndDate());
    }
    maxEndDate = workingDaysCalculator.add(maxEndDate, 2l);
    return maxEndDate;
  }

  /**
   * 
   * @param taskList
   * @param rootTask
   * @param tasks
   */
  private void traverse(
      Set<Task> taskList, 
      Task task, 
      List<Task> tasks) {
    
    taskList.add(task);
    
    TaskTraversalUtility.getDependents(task, tasks).forEach( t -> {
      setupTask(
          t, 
          workingDaysCalculator.add(task.getEndDate(), 2L), 
          task.getIndex() + 1);
      
      traverse(taskList, t, tasks);
    });
    
  }

  /**
   * 
   * @param task
   * @param startDate
   * @param index
   */
  private void setupTask(Task task, Date startDate, int index) {
    task.setIndex(index);
    task.setStartDate(startDate);
    task.setEndDate(workingDaysCalculator.add(startDate, task.getDuration()));
  }

//  /**
//   * 
//   * @param task
//   * @param tasks
//   * @return
//   */
//  private List<Task> getDependents(Task task, List<Task> tasks) {
//    List<Task> dependentTasks = new ArrayList<>();
//    tasks.forEach(t -> {
//      if (t.dependsOn(task)) {
//        dependentTasks.add(t);
//      }
//    });
//    return dependentTasks;
//  }
  
}
