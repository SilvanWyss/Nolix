//package declaration
package ch.nolix.systemapi.timeapi.momentapi;

import ch.nolix.systemapi.elementapi.mainapi.Specified;
import ch.nolix.systemapi.timeapi.timestructure.Month;
import ch.nolix.systemapi.timeapi.timestructure.Weekday;

//interface
public interface ITime extends Specified {

  // method declaration
  /**
   * @return the milliseconds of the current {@link ITime}.
   */
  long getMilliseconds();

  // method declaration
  /**
   * @return the month of the year of the current {@link ITime}.
   */
  Month getMonthOfYear();

  // method declaration
  /**
   * @return the weekday of the current {@link ITime}.
   */
  Weekday getWeekday();

  // method declaration
  /**
   * @param time
   * @return true if the current {@link ITime} is after the given time.
   */
  boolean isAfter(ITime time);

  // method
  /**
   * @param time
   * @return true if the current {@link ITime} is before the given time.
   */
  boolean isBefore(ITime time);
}
