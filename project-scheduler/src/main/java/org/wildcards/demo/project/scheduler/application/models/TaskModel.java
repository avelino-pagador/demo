package org.wildcards.demo.project.scheduler.application.models;

/**
 * 
 * @author user
 *
 */
public class TaskModel {

  /**
   * 
   */
  private Long id;
  
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * 
   * @return
   */
  public String getTaskName() {
    return taskName;
  }

  /**
   * 
   * @param taskName
   */
  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  /**
   * 
   * @return
   */
  public Long getDuration() {
    return duration;
  }

  /**
   * 
   * @param duration
   */
  public void setDuration(Long duration) {
    this.duration = duration;
  }

  /**
   * 
   * @return
   */
  public Long[] getDependencies() {
    return dependencies;
  }

  /**
   * 
   * @param dependencies
   */
  public void setDependencies(Long[] dependencies) {
    this.dependencies = dependencies;
  }
  
  
}
