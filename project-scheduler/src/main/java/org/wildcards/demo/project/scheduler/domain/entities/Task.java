package org.wildcards.demo.project.scheduler.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author user
 *
 */
@Entity
@JsonInclude(Include.NON_NULL)
public class Task implements Serializable {

  /**
   * 
   */
  @Transient
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
  @Column
  private String taskName;
  
  /**
   * 
   */
  @Column
  private Long duration;
  
  /**
   * 
   */
  @Column
  private Integer index;
  
  /**
   * 
   */
  @Column
  @Temporal(TemporalType.DATE)
  private Date startDate;
  
  /**
   * 
   */
  @Column
  @Temporal(TemporalType.DATE)
  private Date endDate;
  
  /**
   * 
   */
  @ManyToMany(
      cascade=CascadeType.ALL,
      fetch=FetchType.LAZY)
  private List<Task> dependencies = new ArrayList<Task>();

  /**
   * 
   */
  public Task() {

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
  public Integer getIndex() {
    return index;
  }
  
  /**
   * 
   * @param index
   */
  public void setIndex(Integer index) {
    this.index = index;
  }
  /**
   * 
   * @return
   */
  public Date getStartDate() {
    return startDate;
  }

  /**
   * 
   * @param startDate
   */
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  /**
   * 
   * @return
   */
  public Date getEndDate() {
    return endDate;
  }

  /**
   * 
   * @param endDate
   */
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  
  /**
   * 
   * @return
   */
  public List<Task> getDependencies() {
    return dependencies;
  }
  
  /**
   * 
   * @param dependencies
   */
  public void setDependencies(List<Task> dependencies) {
    this.dependencies = dependencies;
  }
  
  /**
   * 
   * @param dependency
   */
  public void addDependency(Task dependency) {
    if (!this.dependencies.contains(dependency)) {
      this.dependencies.add(dependency);
    }
  }
  
  /**
   * 
   * @param dependency
   */
  public void removeDependency(Task dependency) {
    if (this.dependencies.contains(dependency)) {
      this.dependencies.remove(dependency);
    }
  }
  
  /**
   * 
   */
  public void removeDependencies() {
    this.dependencies.clear();
  }
  
  /**
   * 
   * @return
   */
  public boolean hasDependency() {
    return !this.dependencies.isEmpty();
  }
  
  
  /**
   * 
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    
    if (!(obj instanceof Task)) {
      return false;
    }
    
    Task task = (Task) obj;
    
    if (this.id.equals(task.id) && 
        this.taskName.equals(task.taskName) &&
        this.duration.equals(task.duration)) {
      return true;
    }
      
    return false;
  }
  
  
  /**
   * 
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }
  
  /**
   * 
   * @param task
   * @return
   */
  public boolean dependsOn(Task task) {
    return this.dependencies.contains(task);
  }
  
  /**
   * 
   */
  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return "{"
        + "\"id\" : " + this.id + ", "
        + "\"taskName\" : \"" + this.taskName + "\", "
        + "\"duration\" : " + this.duration  + ", "
        + "\"startDate\" : \"" + this.startDate + "\", "
        + "\"endDate\" : \"" + this.endDate + "\""
        + "}";
  }
  
}
