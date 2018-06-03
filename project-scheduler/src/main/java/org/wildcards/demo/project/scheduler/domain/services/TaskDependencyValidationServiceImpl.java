package org.wildcards.demo.project.scheduler.domain.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.wildcards.demo.project.scheduler.domain.entities.Task;
import org.wildcards.demo.project.scheduler.domain.exceptions.CircularDependencyException;
import org.wildcards.demo.project.scheduler.domain.services.utils.TaskTraversalUtility;


/**
 * 
 * @author user
 *
 */
@Component
public class TaskDependencyValidationServiceImpl implements TaskDependencyValidationService {

  /**
   * 
   */
  @Override
  public void validateTaskDependencies(List<Task> tasks) {
    
    Task rootTask = TaskTraversalUtility.getRootTask(tasks);
    
    List<Task> traversal = new ArrayList<>();
    traverse(traversal, rootTask, tasks);

    traversal.forEach( t -> {
      System.out.println(t.getTaskName() + ": " + t);
    });
    
//    traverse(traversed, tasks, 0);
  }
  
  /**
   * 
   * @param taskList
   * @param rootTask
   * @param tasks
   */
  private void traverse(
      List<Task> traversal, 
      Task task, 
      List<Task> tasks) {
    
    if (traversal.contains(task)) {
      throw new CircularDependencyException();
    }
    
    traversal.add(task);
    
    TaskTraversalUtility.getDependents(task, tasks).forEach( t -> {
      traverse(traversal, t, tasks);
    });
    
  }
  
//  /**
//   * 
//   * @param traversed
//   * @param tasks
//   */
//  public void traverse(Set<Task> traversed, List<Task> tasks, int level) {
//    
//    Map<Task, Set<Task>> dependencies = new HashMap<>();
//    
//    tasks.forEach(task -> {
//      dependencies.put(task, getDependentTasks(task, tasks));
//    });
//    
//    dependencies.forEach((k, v) -> {
//      System.out.println(k.getTaskName() + ": " + v);
//    });
//    
//  }

  /**
   * 
   * @param task
   * @param tasks
   * @return
   */
  private Set<Task> getDependentTasks(Task task, List<Task> tasks) {
    Set<Task> dependentTasks = new HashSet<>();
    tasks.forEach(t -> {
     if (t.dependsOn(task)) {
       dependentTasks.add(t);
     }
    });
    return dependentTasks;
  }

}
