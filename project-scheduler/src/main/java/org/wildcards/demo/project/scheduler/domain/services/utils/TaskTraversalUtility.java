package org.wildcards.demo.project.scheduler.domain.services.utils;

import java.util.ArrayList;
import java.util.List;

import org.wildcards.demo.project.scheduler.domain.entities.Task;
import org.wildcards.demo.project.scheduler.domain.exceptions.InvalidTaskException;

/**
 * 
 * @author user
 *
 */
public class TaskTraversalUtility {

  /**
   * 
   * @param tasks
   */
  public static Task getRootTask(List<Task> tasks) {
    List<Task> independentTasks = new ArrayList<>();
    tasks.forEach(task -> {
      if (!task.hasDependency()) {
        independentTasks.add(task);
      }
    });
    
    if (independentTasks.isEmpty()) {
      throw new InvalidTaskException("No root task found.");
    }
    
    if (1 < independentTasks.size()) {
      throw new InvalidTaskException("More than one root task is found.");
    }
    
    return independentTasks.get(0);
  }
  
  
  /**
   * 
   * @param task
   * @param tasks
   * @return
   */
  public static List<Task> getDependents(Task task, List<Task> tasks) {
    List<Task> dependentTasks = new ArrayList<>();
    tasks.forEach(t -> {
      if (t.dependsOn(task)) {
        dependentTasks.add(t);
      }
    });
    return dependentTasks;
  }
}
