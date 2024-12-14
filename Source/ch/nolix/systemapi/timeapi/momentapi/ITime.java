package ch.nolix.systemapi.timeapi.momentapi;

import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.elementapi.baseapi.IElement;
import ch.nolix.systemapi.timeapi.timestructureapi.Month;
import ch.nolix.systemapi.timeapi.timestructureapi.Weekday;

public interface ITime extends IElement {

  /**
   * @return the microsecond of the millisecond of the current {@link Time}.
   */
  int getMicrosecondsOfMillisecond();

  /**
   * @return the milliseconds of the current {@link ITime}.
   */
  long getMilliseconds();

  /**
   * @return the month of the year of the current {@link ITime}.
   */
  Month getMonthOfYear();

  /**
   * @return the weekday of the current {@link ITime}.
   */
  Weekday getWeekday();

  /**
   * @param time
   * @return true if the current {@link ITime} is after the given time.
   */
  boolean isAfter(ITime time);

  /**
   * @param time
   * @return true if the current {@link ITime} is before the given time.
   */
  boolean isBefore(ITime time);
}
