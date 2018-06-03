package org.wildcards.demo.project.scheduler.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wildcards.demo.project.scheduler.domain.entities.Project;
import org.wildcards.demo.project.scheduler.domain.services.commands.Command;

/**
 * 
 * @author user
 *
 */
@Component
public class ProjectPlanServiceImpl implements ProjectPlanService {

   
  /**
   * 
   */
  private final Command resetProjectPlanCommand;
  
  /**
   * 
   */
  private final Command generateProjectPlanCommand;
  
  /**
   * 
   */
  @Autowired
  public ProjectPlanServiceImpl(
      Command generateProjectPlanCommand,
      Command resetProjectPlanCommand) {
    this.generateProjectPlanCommand = generateProjectPlanCommand;
    this.resetProjectPlanCommand = resetProjectPlanCommand;
  }
  
  /**
   * 
   */
  @Override
  public void resetSchedule(Project project) {
   resetProjectPlanCommand.execute(project);
  }

  
  
  
  /**
   * 
   */
  @Override
  public void generateSchedule(Project project) {
    generateProjectPlanCommand.execute(project);
  }

}
