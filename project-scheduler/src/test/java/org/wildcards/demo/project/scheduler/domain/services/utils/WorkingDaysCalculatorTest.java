package org.wildcards.demo.project.scheduler.domain.services.utils;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author user
 *
 */
@SuppressWarnings("deprecation")
public class WorkingDaysCalculatorTest {

  /**
   * 
   */
  private WorkingDaysCalculator workingdayCalculator;
  
  /**
   * 
   */
  private Date startDate;
  
  /**
   * 
   */
  private Date endDate;
  
  /**
   * 
   */
  private Date resultDate;
  
  /**
   * 
   */
  private Date expectedDate;
  
  /**
   * 
   */
  private long duration;
  
  
  @Before
  public void setup() throws Exception {
    workingdayCalculator = new WorkingDaysCalculator();
  }
  
  @Test
  public void shouldReturnSameDateWhenDurationIsZero() throws Exception {
    startDate = new Date(2018-1900, 00, 01);
    expectedDate =  new Date(2018-1900, 00, 01);
    duration = 0;

    resultDate = workingdayCalculator.add(startDate, duration);
    
    assertEquals(expectedDate, resultDate);
  }

  
  @Test
  public void shouldReturnSameDateWhenDurationIsOne() throws Exception {
    startDate = new Date(2018-1900, 00, 01);
    expectedDate =  new Date(2018-1900, 00, 01);
    duration = 1;

    resultDate = workingdayCalculator.add(startDate, duration);
    
    assertEquals(expectedDate, resultDate);
  }
  
  @Test
  public void test() throws Exception {
    startDate = new Date(2018-1900, 00, 01);
    expectedDate = new Date(2018-1900, 00, 12);
    duration = 10;

    resultDate = workingdayCalculator.add(startDate, duration);
    
    assertEquals(expectedDate, resultDate);
  }
  
  @Test
  public void maxDateShouldReturnFirstDate() throws Exception {
    startDate = new Date(2018-1900, 00, 01);
    endDate = new Date(2017-1900, 00, 12);
    expectedDate = new Date(2018-1900, 00, 01);
    
    resultDate = workingdayCalculator.maxDate(startDate, endDate);
    
    assertEquals(expectedDate, resultDate);
    
  }
  
  @Test
  public void maxDateShouldReturnSecondDate() throws Exception {
    startDate = new Date(2018-1900, 00, 01);
    endDate = new Date(2017-1900, 00, 12);
    expectedDate = new Date(2018-1900, 00, 01);
    
    resultDate = workingdayCalculator.maxDate(endDate, startDate);
    
    assertEquals(expectedDate, resultDate);
    
  }
}
