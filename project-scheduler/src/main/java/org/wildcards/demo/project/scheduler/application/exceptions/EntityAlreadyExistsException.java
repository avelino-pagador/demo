package org.wildcards.demo.project.scheduler.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author user
 *
 */
@ResponseStatus(code=HttpStatus.NOT_ACCEPTABLE, reason="Entity already exists.")
public class EntityAlreadyExistsException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

}
