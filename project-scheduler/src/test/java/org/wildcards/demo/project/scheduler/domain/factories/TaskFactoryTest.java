package org.wildcards.demo.project.scheduler.domain.factories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.wildcards.demo.project.scheduler.domain.entities.Task;
import org.wildcards.demo.project.scheduler.domain.repositories.TaskRepository;

/**
 * 
 * @author user
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TaskFactoryTest {

  /**
   * 
   */
  @Rule
  public ExpectedException exception = ExpectedException.none();
  
  /**
   * 
   */
  @Mock
  private TaskRepository taskRepository;
  
  /**
   * 
   */
  private TaskFactory taskFactory;
  
  /**
   * 
   */
  private Task task;

  /**
   * 
   */
  private String taskName;

  /**
   * 
   */
  private Long duration;

  /**
   * 
   */
  private Long[] dependencies;

//  /**
//   * 
//   */
//  @Mock
//  private Optional<Task> optionalTask;

  /**
   * 
   */
  @Mock
  private Task dependencyTask;
  
  /**
   * 
   * @throws Exception
   */
  @Before
  public void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
    taskFactory = new TaskFactory(taskRepository);
  }
  
  /**
   * 
   */
  @Test
  public void createInstanceShouldThrowExceptionWhenTaskNameIsNull() {
    
    taskName = null;
    duration = 0L;
    dependencies = null;
    
    exception.expect(NullPointerException.class);
    exception.expectMessage("Task name cannot be null.");
    
    taskFactory.createInstance(taskName, duration, dependencies);
  }
  
  /**
   * 
   */
  @Test
  public void createInstanceShouldThrowExceptionWhenTaskNameIsEmpty() {
    
    taskName = "";
    duration = 0L;
    dependencies = null;
    
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Task name cannot be empty.");
    
    taskFactory.createInstance(taskName, duration, dependencies);
  }
  
  /**
   * 
   */
  @Test
  public void createInstanceShouldThrowExceptionWhenDurationIsNegative() {
    
    taskName = "task name";
    duration = -1L;
    dependencies = null;
    
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Task duration must be greater than zero.");
    
    taskFactory.createInstance(taskName, duration, dependencies);
  }
  
  /**
   * 
   */
  @Test
  public void createInstanceShouldThrowExceptionWhenDurationIsZero() {
    
    taskName = "task name";
    duration = 0L;
    dependencies = null;
    
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Task duration must be greater than zero.");
    
    taskFactory.createInstance(taskName, duration, dependencies);
  }
  
  /**
   * 
   */
  @Test
  public void createInstanceShouldReturnInstanceWhenDependenciesIsNull() {
    
    taskName = "task name";
    duration = 1L;
    dependencies = null;
    
    task = taskFactory.createInstance(taskName, duration, dependencies);
    
    assertNotNull(task);
    assertEquals(taskName, task.getTaskName());
    assertEquals(duration, task.getDuration());
    assertEquals(0, task.getDependencies().size());
  }
  
  
  /**
   * 
   */
  @Test
  public void createInstanceShouldReturnInstanceWhenDependenciesIsEmpty() {
    
    taskName = "task name";
    duration = 1L;
    dependencies = new Long[0];
    
    task = taskFactory.createInstance(taskName, duration, dependencies);
    
    assertNotNull(task);
    assertEquals(taskName, task.getTaskName());
    assertEquals(duration, task.getDuration());
    assertEquals(0, task.getDependencies().size());
  }
  
  
//  /**
//   * 
//   */
//  @Test
//  public void createInstanceShouldReturnInstanceWhenDependenciesIsNotEmpty() {
//    
//    taskName = "task name";
//    duration = 1L;
//    dependencies = new Long[] {1L};
//    
//    task = taskFactory.createInstance(taskName, duration, dependencies);
//    
//    when(taskRepository.findById(any(Long.class))).thenReturn(optionalTask);
//    when(optionalTask.isPresent()).thenReturn(true);
//    when(optionalTask.get()).thenReturn(dependencyTask);
//    
//    assertNotNull(task);
//    assertEquals(taskName, task.getTaskName());
//    assertEquals(duration, task.getDuration());
//    assertEquals(0, task.getDependencies().size());
//  }
}
