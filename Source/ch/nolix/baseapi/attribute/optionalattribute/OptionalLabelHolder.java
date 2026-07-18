/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * A {@link OptionalLabelHolder} can have a label.
 * 
 * @author Silvan Wyss
 */
public interface OptionalLabelHolder {
  /**
   * @return the label of the current {@link OptionalLabelHolder}
   * @throws RuntimeException if the current {@link OptionalLabelHolder} does not
   *                          have a label
   */
  String getLabel();

  /**
   * @return the label of the current {@link OptionalLabelHolder} in single quotes
   * @throws RuntimeException if the current {@link OptionalLabelHolder} does not
   *                          have a label
   */
  default String getLabelInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getLabel() + StringCatalog.SINGLE_QUOTE;
  }

  /**
   * @return true if the current {@link OptionalLabelHolder} has a label, false
   *         otherwise
   */
  boolean hasLabel();
}
