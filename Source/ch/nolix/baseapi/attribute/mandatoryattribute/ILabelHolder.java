/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

import ch.nolix.baseapi.commontypetool.stringtool.StringCatalog;

/**
 * A {@link ILabelHolder} has a label.
 * 
 * @author Silvan Wyss
 */
public interface ILabelHolder {
  /**
   * @return the label of the current {@link ILabelHolder}
   */
  String getLabel();

  /**
   * @return the label of the current {@link ILabelHolder} in single quotes
   */
  default String getLabelInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getLabel() + StringCatalog.SINGLE_QUOTE;
  }
}
