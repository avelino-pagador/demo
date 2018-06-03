package org.wildcards.demo.project.scheduler.domain.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.wildcards.demo.project.scheduler.domain.entities.Project;

/**
 * 
 * @author user
 *
 */
@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{
  
  /**
   * 
   * @param projectName
   * @return
   */
  public Project getProjectByProjectName(String projectName);
  
}
