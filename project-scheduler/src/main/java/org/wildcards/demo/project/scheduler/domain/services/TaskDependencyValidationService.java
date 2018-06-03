package org.wildcards.demo.project.scheduler.domain.services;

import java.util.List;

import org.wildcards.demo.project.scheduler.domain.entities.Task;

/**
 * 
 * @author user
 *
 */
public interface TaskDependencyValidationService {
  
  /**
   * 
   * @param tasks
   */
  public void validateTaskDependencies(List<Task> tasks);
  
  
}
