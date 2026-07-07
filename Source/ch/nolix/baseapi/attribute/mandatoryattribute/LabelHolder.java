/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * A {@link LabelHolder} has a label.
 * 
 * @author Silvan Wyss
 */
public interface LabelHolder {
  /**
   * @return the label of the current {@link LabelHolder}
   */
  String getLabel();

  /**
   * @return the label of the current {@link LabelHolder} in single quotes
   */
  default String getLabelInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getLabel() + StringCatalog.SINGLE_QUOTE;
  }
}
