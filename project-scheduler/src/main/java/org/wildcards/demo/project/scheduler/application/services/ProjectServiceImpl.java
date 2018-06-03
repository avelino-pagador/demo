package org.wildcards.demo.project.scheduler.application.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.wildcards.demo.project.scheduler.application.exceptions.EntityAlreadyExistsException;
import org.wildcards.demo.project.scheduler.application.exceptions.EntityNotFoundException;
import org.wildcards.demo.project.scheduler.application.models.ProjectModel;
import org.wildcards.demo.project.scheduler.application.models.TaskModel;
import org.wildcards.demo.project.scheduler.domain.entities.Project;
import org.wildcards.demo.project.scheduler.domain.entities.Task;
import org.wildcards.demo.project.scheduler.domain.factories.ProjectFactory;
import org.wildcards.demo.project.scheduler.domain.factories.TaskFactory;
import org.wildcards.demo.project.scheduler.domain.repositories.ProjectRepository;
import org.wildcards.demo.project.scheduler.domain.repositories.TaskRepository;
import org.wildcards.demo.project.scheduler.domain.services.ProjectPlanService;
import org.wildcards.demo.project.scheduler.domain.services.TaskDependencyValidationService;

/**
 * 
 * @author user
 *
 */
@Component("projectService")
public class ProjectServiceImpl implements ProjectService {

  /**
   * 
   */
  private final ProjectRepository projectRepository;
  
  /**
   * 
   */
  private final ProjectPlanService projectPlanService;
  
  /**
   * 
   */
  private final TaskDependencyValidationService validationService;
  
  /**
   * 
   */
  private final ProjectFactory projectFactory;
  
  /**
   * 
   */
  private final TaskFactory taskFactory;
  
  /**
   * 
   */
  private final TaskRepository taskRepository;
  
  /**
   * 
   * @param projectRepository
   */
  @Autowired
  public ProjectServiceImpl(
      ProjectRepository projectRepository, 
      ProjectPlanService projectPlanService,
      TaskDependencyValidationService validationService,
      ProjectFactory projectFactory,
      TaskRepository taskRepository,
      TaskFactory taskFactory) {
    this.projectRepository = projectRepository;
    this.projectPlanService = projectPlanService;
    this.validationService = validationService;
    this.projectFactory = projectFactory;
    this.taskFactory = taskFactory;
    this.taskRepository = taskRepository;
  }
  
  /**
   * 
   */
  public List<Project> getProjects(int page, int size) {
    Page<Project> projectPage = projectRepository.findAll(
        createPageableRequest(page, size));
    
    return projectPage.getContent();
  }

  /**
   * 
   */
  @Override
  public Project getProjectById(Long projectId) {
    return getProject(projectId);
  }

  /**
   * 
   */
  @Override
  @Transactional
  public void generateSchedule(Long projectId) {
    Project project = getProject(projectId);
    
    validationService.validateTaskDependencies(project.getTasks());
    
    projectPlanService.generateSchedule(project);
  }
  
  /**
   * 
   */
  @Override
  @Transactional
  public void resetSchedule(Long projectId) {
    projectPlanService.resetSchedule(getProject(projectId));
  }
  
  /**
   * 
   * @param page
   * @param size
   * @return
   */
  @SuppressWarnings("deprecation")
  private PageRequest createPageableRequest(int page, int size) {
    return new PageRequest(page, size);
  }
  
  /**
   * 
   */
  @Override
  @Transactional
  public void createProject(ProjectModel project) {
    if (null != projectRepository.getProjectByProjectName(project.getProjectName())) {
      throw new EntityAlreadyExistsException();
    }
    
    Project newProject = projectFactory.createInstance(
        project.getProjectName(), 
        project.getProjectDescription());
    
    projectRepository.save(newProject);
  }

  /**
   * 
   */
  @Override
  @Transactional
  public void deleteProject(Long projectId) {
    projectRepository.deleteById(projectId);
  }

  /**
   * 
   * @param projectId
   * @param task
   */
  @Override
  @Transactional
  public void addTaskToProject(Long projectId, TaskModel task) {
    Project project = getProject(projectId);
    
    Task newTask = taskFactory.createInstance(
        task.getTaskName(), 
        task.getDuration(), 
        task.getDependencies());
    
    newTask = taskRepository.save(newTask);
    
    project.addTask(newTask);
    
    projectRepository.save(project);
    
  }

  /**
   * 
   * @param projectId
   * @param taskId
   */
  @Override
  @Transactional
  public void deleteTaskFromProject(Long projectId, Long taskId) {
    Project project = getProject(projectId);
    Task task = getTask(taskId);
   
    project.removeTask(task);
    projectRepository.save(project);
    
    taskRepository.delete(task);
  }
  
  /**
   * 
   */
  @Override
  @Transactional
  public void updateTaskToProject(Long projectId, TaskModel task) {
    Project project = getProject(projectId);
    Task repoTask = getTask(task.getId());
   
    repoTask.setTaskName(task.getTaskName());
    repoTask.setDuration(task.getDuration());
    repoTask.removeDependencies();
    
    for (Long id : task.getDependencies()) {
      repoTask.addDependency(getTask(id));
    }
    
    taskRepository.save(repoTask);
  }
  
  /**
   * 
   * 
   * @param taskId
   * @return
   */
  private Task getTask(Long taskId) {
    Optional<Task> task = taskRepository.findById(taskId);
    if (!task.isPresent()) {
      throw new EntityNotFoundException();
    }
    return task.get();
  }

  /**
   * 
   * @param projectId
   * @return
   */
  private Project getProject(Long projectId) {
    Optional<Project> project = projectRepository.findById(projectId);
    if (!project.isPresent()) {
      throw new EntityNotFoundException();
    }
    return project.get();
  }
  
}
