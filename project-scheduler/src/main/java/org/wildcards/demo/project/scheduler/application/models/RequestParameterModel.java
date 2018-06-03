package org.wildcards.demo.project.scheduler.application.models;

import org.wildcards.demo.project.scheduler.application.enums.ProjectAction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author user
 *
 */
@JsonInclude(Include.NON_NULL)
public class RequestParameterModel {
  
  /**
   * 
   */
  private ProjectAction action;
  
  /**
   * 
   * @return
   */
  public ProjectAction getAction() {
    return action;
  }

  /**
   * 
   * @param action
   */
  public void setAction(ProjectAction action) {
    this.action = action;
  }
  
}
