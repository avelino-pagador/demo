package org.wildcards.demo.project.scheduler.application.services;

import java.util.List;

import org.wildcards.demo.project.scheduler.application.models.ProjectModel;
import org.wildcards.demo.project.scheduler.application.models.TaskModel;
import org.wildcards.demo.project.scheduler.domain.entities.Project;

/**
 * 
 * @author user
 *
 */
public interface ProjectService {

  /**
   * 
   * @param page
   * @param size
   * @return
   */
  public List<Project> getProjects(int page, int size);
  
  /**
   * 
   * @param projectId
   * @return
   */
  public Project getProjectById(Long projectId);
  
  /**
   * 
   * @param projectId
   * @return
   */
  public void deleteProject(Long projectId);
  
  /**
   * 
   * @param projectId
   * @param date 
   */
  public void generateSchedule(Long projectId);
  
  /**
   * 
   * @param projectId
   */
  public void resetSchedule(Long projectId);

  /**
   * 
   * @param project
   */
  public void createProject(ProjectModel project);
  
  /**
   * 
   * @param projectId
   * @param task
   */
  public void addTaskToProject(Long projectId, TaskModel task);

  /**
   * 
   * @param projectId
   * @param taskId
   */
  public void deleteTaskFromProject(Long projectId, Long taskId);

  /**
   * 
   * @param projectId
   * @param task
   */
  public void updateTaskToProject(Long projectId, TaskModel task);
  
}
