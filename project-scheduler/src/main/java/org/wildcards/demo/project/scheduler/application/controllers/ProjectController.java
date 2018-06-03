package org.wildcards.demo.project.scheduler.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wildcards.demo.project.scheduler.application.models.ProjectModel;
import org.wildcards.demo.project.scheduler.application.models.RequestParameterModel;
import org.wildcards.demo.project.scheduler.application.models.TaskModel;
import org.wildcards.demo.project.scheduler.application.services.ProjectService;
import org.wildcards.demo.project.scheduler.domain.entities.Project;

/**
 * 
 * @author user
 *
 */
@RestController
public class ProjectController {

  /**
   * 
   */
  private final ProjectService projectService;
 
  /**
   * 
   * @param projectService
   */
  @Autowired
  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }
  
  /**
   * 
   * @return
   */
  @GetMapping("/projects")
  public ResponseEntity<List<Project>> getProjects(
      @RequestParam(defaultValue="0") int page,
      @RequestParam(defaultValue="100") int size) {

    return new ResponseEntity<List<Project>>(
        projectService.getProjects(page, size), HttpStatus.OK);
  }
  
  /**
   * 
   * @return
   */
  @PostMapping(
      value="/projects",
      consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
  public void addProject(@RequestBody ProjectModel project) {
    projectService.createProject(project);
  }
  
  /**
   * 
   * @return
   */
  @GetMapping("/projects/{projectId}")
  public ResponseEntity<Project> getProject(@PathVariable Long projectId) {
    return new ResponseEntity<Project>(
        projectService.getProjectById(projectId), HttpStatus.OK);
  }
  
  /**
   * 
   * @return
   */
  @DeleteMapping("/projects/{projectId}")
  public void deleteProject(@PathVariable Long projectId) {
    projectService.deleteProject(projectId);
  }
  
  /**
   * 
   * @return
   */
  @DeleteMapping("/projects/{projectId}/tasks/{taskId}")
  public void deleteTask(
      @PathVariable Long projectId,
      @PathVariable Long taskId) {
    projectService.deleteTaskFromProject(projectId, taskId);
  }
  
  /**
   * 
   * @return
   */
  @PostMapping(
      value="/projects/{projectId}",
      consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
  public void manageSchedule(
      @PathVariable Long projectId,
      @RequestBody RequestParameterModel requestParameter) {
    switch (requestParameter.getAction()) {
      case GENERATE:
        projectService.generateSchedule(projectId);
        break;
      case RESET:
        projectService.resetSchedule(projectId);
        break;
    }
  }
  
  /**
   * 
   * @return
   */
  @PostMapping(
      value="/projects/{projectId}/tasks",
      consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
  public void addTask(
      @PathVariable Long projectId,
      @RequestBody TaskModel task) {
    projectService.addTaskToProject(projectId, task);
  }
  
  /**
   * 
   * @return
   */
  @PutMapping(
      value="/projects/{projectId}/tasks/{taskId}",
      consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
  public void addTask(
      @PathVariable Long projectId,
      @PathVariable Long taskId,
      @RequestBody TaskModel task) {
    projectService.updateTaskToProject(projectId, task);
  }
  
}
