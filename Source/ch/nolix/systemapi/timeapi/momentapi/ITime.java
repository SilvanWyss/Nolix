//package declaration
package ch.nolix.systemapi.timeapi.momentapi;

//own imports
import ch.nolix.systemapi.elementapi.baseapi.IElement;
import ch.nolix.systemapi.timeapi.timestructureapi.Month;
import ch.nolix.systemapi.timeapi.timestructureapi.Weekday;

//interface
public interface ITime extends IElement {

  //method declaration
  /**
   * @return the milliseconds of the current {@link ITime}.
   */
  long getMilliseconds();

  //method declaration
  /**
   * @return the month of the year of the current {@link ITime}.
   */
  Month getMonthOfYear();

  //method declaration
  /**
   * @return the weekday of the current {@link ITime}.
   */
  Weekday getWeekday();

  //method declaration
  /**
   * @param time
   * @return true if the current {@link ITime} is after the given time.
   */
  boolean isAfter(ITime time);

  //method
  /**
   * @param time
   * @return true if the current {@link ITime} is before the given time.
   */
  boolean isBefore(ITime time);
}
