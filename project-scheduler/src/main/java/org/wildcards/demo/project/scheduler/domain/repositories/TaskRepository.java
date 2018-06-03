package org.wildcards.demo.project.scheduler.domain.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.wildcards.demo.project.scheduler.domain.entities.Task;

/**
 * 
 * @author user
 *
 */
@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long>{
  
}
