package org.wildcards.demo.project.scheduler.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author user
 *
 */
@Entity(name="project")
@JsonInclude(Include.NON_NULL)
public class Project implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  
  /**
   * 
   */
  @Column(unique=true)
  private String projectName;
  
  /**
   * 
   */
  @Column
  private String projectDescription;
  
  /**
   * 
   */
  @OneToMany(
      cascade=CascadeType.ALL,
      orphanRemoval=true,
      fetch=FetchType.LAZY)
  private List<Task> tasks = new ArrayList<>();
  
  /**
   * 
   */
  public Project() {
    
  }
  
  /**
   * 
   * @return
   */
  public Long getId() {
    return id;
  }

  /**
   * 
   * @param id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * 
   * @return
   */
  public String getProjectName() {
    return projectName;
  }

  /**
   * 
   * @param projectName
   */
  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  /**
   * 
   * @return
   */
  public String getProjectDescription() {
    return projectDescription;
  }

  /**
   * 
   * @param projectDescription
   */
  public void setProjectDescription(String projectDescription) {
    this.projectDescription = projectDescription;
  }

  /**
   * 
   * @return
   */
  @Transient
  public List<Task> getTasks() {
    return tasks;
  }

  /**
   * 
   * @param tasks
   */
  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }
  
  /**
   * 
   * @param task
   */
  public void addTask(Task task) {
    if (!tasks.contains(task)) {
      tasks.add(task);
    }
  }
  
  /**
   * 
   * @param task
   */
  public void removeTask(Task task) {
    if (tasks.contains(task)) {
      tasks.remove(task);
    }
  }
  
  /**
   * 
   */
  public void removeTasks() {
    tasks.clear();
  }
}
