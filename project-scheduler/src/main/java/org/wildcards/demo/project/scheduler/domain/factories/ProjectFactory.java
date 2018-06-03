package org.wildcards.demo.project.scheduler.domain.factories;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;
import org.wildcards.demo.project.scheduler.domain.entities.Project;

/**
 * 
 * @author user
 *
 */
@Component
public class ProjectFactory {

  /**
   * 
   * @param projectName
   * @param projectDescription
   * @return
   */
  public Project createInstance(
      String projectName, 
      String projectDescription) {
    
    validateProjectName(projectName);
    validateProjectDescription(projectDescription);
    
    Project project = new Project();
    project.setProjectName(projectName);
    project.setProjectDescription(projectDescription);
    return project;
  }

  /**
   * 
   * @param projectDescription
   */
  private void validateProjectDescription(String projectDescription) {
    Validate.notNull(projectDescription, "Project description cannot be null.");
    Validate.notEmpty(projectDescription, "Project description cannot be empty.");
  }

  /**
   * 
   * @param projectName
   */
  private void validateProjectName(String projectName) {
    Validate.notNull(projectName, "Project name cannot be null.");
    Validate.notEmpty(projectName, "Project name cannot be empty.");
  }
  
}
