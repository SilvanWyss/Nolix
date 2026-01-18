/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.time.timestructure;

import ch.nolix.coreapi.document.node.INode;

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

  public static Month fromJavaMonth(final java.time.Month month) { //NOSONAR: This method is uniform.
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

  public Month fromSpecification(final INode<?> specification) {
    return Month.valueOf(specification.getSingleChildNodeHeader());
  }
}
