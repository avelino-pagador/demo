package org.wildcards.demo.project.scheduler.domain.factories;

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
  public Project createInstance(String projectName, String projectDescription) {
    Project project = new Project();
    project.setProjectName(projectName);
    project.setProjectDescription(projectDescription);
    return project;
  }
  
}
