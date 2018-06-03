package org.wildcards.demo.project.scheduler.domain.services.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wildcards.demo.project.scheduler.domain.entities.Project;
import org.wildcards.demo.project.scheduler.domain.repositories.ProjectRepository;

/**
 * 
 * @author user
 *
 */
@Component
public class ResetProjectPlanCommand implements Command {

  
  /**
   * 
   */
  private final ProjectRepository projectRepository;
  
  
  /**
   * 
   * @param projectRepository
   */
  @Autowired
  public ResetProjectPlanCommand(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }
  
  /**
   * 
   */
  @Override
  public void execute(Project project) {
    
    project.getTasks().forEach( task-> {
      task.setStartDate(null);
      task.setEndDate(null);
      task.setIndex(null);
    });
    
    projectRepository.save(project);
    
  }

}
