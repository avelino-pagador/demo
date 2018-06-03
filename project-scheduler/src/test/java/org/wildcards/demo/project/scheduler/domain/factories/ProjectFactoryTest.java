package org.wildcards.demo.project.scheduler.domain.factories;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * @author user
 *
 */
public class ProjectFactoryTest {

  /**
   * 
   */
  @Rule
  public ExpectedException exception = ExpectedException.none();
  
  /**
   * 
   */
  private ProjectFactory projectFactory;
  
  /**
   * 
   */
  private String projectName;
  
  /**
   * 
   */
  private String projectDescription;
  
  /**
   * 
   * @throws Exception
   */
  @Before
  public void setup() throws Exception {
    projectFactory = new ProjectFactory();
  }
  
  /**
   * 
   * @throws Exception
   */
  @Test
  public void createInstanceShouldThrowExceptionWhenProjectNameIsNull() throws Exception {
     
    exception.expect(NullPointerException.class);
    exception.expectMessage("Project name cannot be null.");
    
    projectName = null;
    projectDescription = null;
    
    projectFactory.createInstance(projectName, projectDescription);
   
  }
  
  /**
   * 
   * @throws Exception
   */
  @Test
  public void createInstanceShouldThrowExceptionWhenProjectNameIsEmpty() throws Exception {
     
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Project name cannot be empty.");
    
    projectName = "";
    projectDescription = null;
    
    projectFactory.createInstance(projectName, projectDescription);
   
  }
  
  /**
   * 
   * @throws Exception
   */
  @Test
  public void createInstanceShouldThrowExceptionWhenProjectDescriptionIsNull() throws Exception {
     
    exception.expect(NullPointerException.class);
    exception.expectMessage("Project description cannot be null.");
    
    projectName = "project name";
    projectDescription = null;
    
    projectFactory.createInstance(projectName, projectDescription);
   
  }
  
  /**
   * 
   * @throws Exception
   */
  @Test
  public void createInstanceShouldThrowExceptionWhenProjectDescriptionIsEmpty() throws Exception {
     
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Project description cannot be empty.");
    
    projectName = "project name";
    projectDescription = "";
    
    projectFactory.createInstance(projectName, projectDescription);
   
  }
  
}
