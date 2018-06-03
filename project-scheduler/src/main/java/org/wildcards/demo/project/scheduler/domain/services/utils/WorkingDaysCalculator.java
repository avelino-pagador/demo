package org.wildcards.demo.project.scheduler.domain.services.utils;

import java.util.Date;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

/**
 * 
 * @author user
 *
 */
@Component
public class WorkingDaysCalculator {

  /**
   * 
   * @param endDate
   * @param duration
   * @return
   */
  public Date add(Date date, Long duration) {    
    LocalDate endDate = new LocalDate(date.getTime());
    if (duration > 1) {
      for (int i=0; i<duration-1; i++) {
        endDate = endDate.plusDays(1);
        if (endDate.getDayOfWeek()>5) {
          endDate = endDate.plusDays(2);
        }
      }
    } else {
      // if start date is a weekend
      if (endDate.getDayOfWeek()>5) {
        endDate = endDate.plusDays(2);
      }
    }
    
    return endDate.toDate();
  }

  
  /**
   * 
   * @param date1
   * @param date2
   * @return
   */
  public Date maxDate(Date date1, Date date2) {
    if (date1.compareTo(date2)>=0) {
      return date1;
    }
    return date2;
  }
}
