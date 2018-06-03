package org.wildcards.demo.project.scheduler.domain.factories;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.Validate;
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
    
    Validate.notNull(taskName, "Task name cannot be null.");
    Validate.notEmpty(taskName, "Task name cannot be empty.");
    
    Validate.isTrue(duration>=1, "Task duration must be greater than zero.");
    
    Task task = new Task();
    task.setTaskName(taskName);
    task.setDuration(duration);
    
    if (null != dependencies) {
      for (Long dependency : dependencies) {
        Optional<Task> dependencyTask = taskRepository.findById(dependency);
        if (!dependencyTask.isPresent()) {
          throw new EntityNotFoundException();
        }
        task.addDependency(dependencyTask.get());
      }
    }
    
    return task;
  }
}
