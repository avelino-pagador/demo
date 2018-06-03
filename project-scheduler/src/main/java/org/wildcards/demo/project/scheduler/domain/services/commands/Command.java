package org.wildcards.demo.project.scheduler.domain.services.commands;

import org.wildcards.demo.project.scheduler.domain.entities.Project;

/**
 * 
 * @author user
 *
 */
public interface Command {
  
  /**
   * 
   * @param project
   */
  public void execute(Project project);
}
