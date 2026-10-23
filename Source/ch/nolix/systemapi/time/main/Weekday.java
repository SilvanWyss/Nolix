/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.time.main;

import java.time.DayOfWeek;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum Weekday {
  MONDAY,
  TUESDAY,
  WEDNESDAY,
  THURSDAY,
  FRIDAY,
  SATURDAY,
  SUNDAY;

  /**
   * @param dayOfWeek
   * @return the {@link Weekday} that corresponds to the given dayOfWeek
   */
  public static Weekday fromDayOfWeek(final DayOfWeek dayOfWeek) {
    return switch (dayOfWeek) {
      case MONDAY ->
        MONDAY;
      case TUESDAY ->
        TUESDAY;
      case WEDNESDAY ->
        WEDNESDAY;
      case THURSDAY ->
        THURSDAY;
      case FRIDAY ->
        FRIDAY;
      case SATURDAY ->
        SATURDAY;
      case SUNDAY ->
        SUNDAY;
      default ->
        throw new IllegalArgumentException("The given day of week '" + dayOfWeek + "' is not valid.");
    };
  }

  /**
   * @param specification
   * @return the {@link Weekday} the given specification represents
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link Weekday}
   */
  public static Weekday fromSpecification(final Node<?> specification) {
    return Weekday.valueOf(specification.getSingleChildNodeHeader());
  }
}
