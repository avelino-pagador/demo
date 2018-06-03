package org.wildcards.demo.project.scheduler.domain.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.wildcards.demo.project.scheduler.domain.entities.Task;


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
    Set<Task> traversed = new HashSet<>();
    
    traverse(traversed, tasks, 0);
    
    
  }
  
  
  /**
   * 
   * @param traversed
   * @param tasks
   */
  public void traverse(Set<Task> traversed, List<Task> tasks, int level) {
    
    Map<Task, Set<Task>> dependencies = new HashMap<>();
    
    tasks.forEach(task -> {
      dependencies.put(task, getDependentTasks(task, tasks));
    });
    
    
    dependencies.forEach((k, v) -> {
      System.out.println(k.getTaskName() + ": " + v);
    });
    
  }

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
