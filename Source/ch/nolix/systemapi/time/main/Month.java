/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.time.main;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum Month {
  JANUARY,
  FEBRUARY,
  MARCH,
  APRIL,
  MAY,
  JUNE,
  JULY,
  AUGUST,
  SEPTEMBER,
  OCTOBER,
  NOVEMBER,
  DECEMBER;

  /**
   * @param month
   * @return the {@link Month} that corresponds to the given month
   */
  public static Month fromJavaMonth(final java.time.Month month) { // NOSONAR: This method is uniform.
    return switch (month) {
      case JANUARY ->
        JANUARY;
      case FEBRUARY ->
        FEBRUARY;
      case MARCH ->
        MARCH;
      case APRIL ->
        APRIL;
      case MAY ->
        MAY;
      case JUNE ->
        JUNE;
      case JULY ->
        JULY;
      case AUGUST ->
        AUGUST;
      case SEPTEMBER ->
        SEPTEMBER;
      case OCTOBER ->
        OCTOBER;
      case NOVEMBER ->
        NOVEMBER;
      case DECEMBER ->
        DECEMBER;
      default ->
        throw new IllegalArgumentException("The given month '" + month + "' is not valid.");
    };
  }

  /**
   * @param specification
   * @return the {@link Month} the given specification represents
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link Month}
   */
  public Month fromSpecification(final Node<?> specification) {
    return Month.valueOf(specification.getSingleChildNodeHeader());
  }
}
