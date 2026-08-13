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

  public static Weekday fromSpecification(final Node<?> specification) {
    return Weekday.valueOf(specification.getSingleChildNodeHeader());
  }
}
