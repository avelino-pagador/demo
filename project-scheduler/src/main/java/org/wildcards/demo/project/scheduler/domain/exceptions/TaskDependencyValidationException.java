package org.wildcards.demo.project.scheduler.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author user
 *
 */
@ResponseStatus(code=HttpStatus.NOT_ACCEPTABLE, reason="Invalid task dependency found.")
public class TaskDependencyValidationException extends DomainException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

}
