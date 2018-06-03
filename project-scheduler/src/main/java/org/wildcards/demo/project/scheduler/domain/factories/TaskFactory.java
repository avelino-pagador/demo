package org.wildcards.demo.project.scheduler.domain.factories;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Component;
import org.wildcards.demo.project.scheduler.domain.entities.Task;
import org.wildcards.demo.project.scheduler.domain.repositories.TaskRepository;

/**
 * 
 * @author user
 *
 */
@Component
public class TaskFactory {
  
  /**
   * 
   */
  private final TaskRepository taskRepository;
  
  /**
   * 
   * @param taskRepository
   */
  public TaskFactory(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  /**
   * 
   * @param taskName
   * @param duration
   * @param dependencies
   * @return
   */
  public Task createInstance(String taskName, Long duration, Long[] dependencies) {
    Task task = new Task();
    task.setTaskName(taskName);
    task.setDuration(duration);
    
    for (Long dependency : dependencies) {
      Optional<Task> dependencyTask = taskRepository.findById(dependency);
      if (!dependencyTask.isPresent()) {
        throw new EntityNotFoundException();
      }
      
      task.addDependency(dependencyTask.get());
    }
    
    return task;
  }
}
