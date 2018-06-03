package org.wildcards.demo.project.scheduler.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author user
 *
 */
@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason="Not found.")
public class InvalidTaskException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

}
