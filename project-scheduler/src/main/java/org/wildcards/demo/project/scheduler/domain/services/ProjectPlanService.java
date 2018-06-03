package org.wildcards.demo.project.scheduler.domain.services;

import org.wildcards.demo.project.scheduler.domain.entities.Project;

/**
 * 
 * @author user
 *
 */
public interface ProjectPlanService {

  /**
   * 
   * @param project
   */
  public void resetSchedule(Project project);
  
  
  /**
   * 
   * @param project
   */
  public void generateSchedule(Project project);
  
}
