/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.time.moment;

import ch.nolix.systemapi.element.base.IElement;
import ch.nolix.systemapi.time.timestructure.Month;
import ch.nolix.systemapi.time.timestructure.Weekday;

/**
 * A {@link ITime} stores a time with a precision of 1 microsecond.
 * 
 * @author Silvan Wyss
 */
public interface ITime extends IElement {
  /**
   * @return the day of the month of the current {@link ITime}.
   */
  int getDayOfMonth();

  /**
   * @return the hour of the day of the current {@link ITime}.
   */
  int getHourOfDay();

  /**
   * @return the microsecond of the millisecond of the current {@link ITime}.
   */
  int getMicrosecondOfMillisecond();

  /**
   * @return the millisecond of the second of the current {@link ITime}.
   */
  int getMillisecondOfSecond();

  /**
   * @return the minute of the hour of the current {@link ITime}.
   */
  int getMinuteOfHour();

  /**
   * @return the month of the year of the current {@link ITime}.
   */
  Month getMonthOfYear();

  /**
   * @return the month of the year of the current {@link ITime} as int.
   */
  int getMonthOfYearAsInt();

  /**
   * @return the second of the minute of the current {@link ITime}.
   */
  int getSecondOfMinute();

  /**
   * @return the weekday of the current {@link ITime}.
   */
  Weekday getWeekday();

  /**
   * @return the week of the year of the current {@link ITime}.
   */
  int getWeekOfYear();

  /**
   * @return the year of the current {@link ITime}.
   */
  int getYear();

  /**
   * @param time
   * @return true if the current {@link ITime} is after the given time, false
   *         otherwise
   */
  boolean isAfter(ITime time);

  /**
   * @param time
   * @return true if the current {@link ITime} is before the given time, false
   *         otherwise
   */
  boolean isBefore(ITime time);

  /**
   * @return true if the current {@link ITime} is in a leap year, false otherwise.
   */
  boolean isInLeapYear();

  /**
   * @return the seconds of the current {@link ITime}.
   */
  long toSeconds();

  /**
   * @param days
   * @return a new {@link ITime} with the given days added to or subtracted from
   *         the current {@link ITime}.
   */
  ITime withAddedOrSubtractedDays(int days);

  /**
   * @param hours
   * @return a new {@link ITime} with the given hours added to or subtracted from
   *         the current {@link ITime}.
   */
  ITime withAddedOrSubtractedHours(int hours);

  /**
   * @param microseconds
   * @return a new {@link ITime} with the given microseconds added to or
   *         subtracted from the current {@link ITime}.
   */
  ITime withAddedOrSubtractedMicroseconds(long microseconds);

  /**
   * @param milliseconds
   * @return a new {@link ITime} with the given milliseconds added to or
   *         subtracted from to the current {@link ITime}.
   */
  ITime withAddedOrSubtractedMilliseconds(int milliseconds);

  /**
   * @param minutes
   * @return a new {@link ITime} with the given minutes added to or subtracted
   *         from the current {@link ITime}.
   */
  ITime withAddedOrSubtractedMinutes(int minutes);

  /**
   * @param seconds
   * @return a new {@link ITime} with the given seconds added to or subtracted
   *         from the current {@link ITime}.
   */
  ITime withAddedOrSubtractedSeconds(int seconds);
}
