/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link ILabelHolder} has a label.
 * 
 * @author Silvan Wyss
 */
public interface ILabelHolder {
  /**
   * @return the label of the current {@link ILabelHolder}.
   */
  String getLabel();

  /**
   * @return the label of the current {@link ILabelHolder} in quotes.
   */
  default String getLabelInQuotes() {
    return ("'" + getLabel() + "'");
  }
}
