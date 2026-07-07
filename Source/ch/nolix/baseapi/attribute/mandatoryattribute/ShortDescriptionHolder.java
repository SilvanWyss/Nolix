/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * A {@link ShortDescriptionHolder} has a short description.
 * 
 * @author Silvan Wyss
 */
public interface ShortDescriptionHolder {
  /**
   * @return the short description of the current {@link ShortDescriptionHolder}
   */
  String getShortDescription();

  /**
   * @return the short description of the current {@link ShortDescriptionHolder}
   *         in single quotes
   */
  default String getShortDescriptionInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getShortDescription() + StringCatalog.SINGLE_QUOTE;
  }
}
