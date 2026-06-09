/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

import ch.nolix.baseapi.commontypetool.stringtool.StringCatalog;

/**
 * A {@link IShortDescriptionHolder} has a short description.
 * 
 * @author Silvan Wyss
 */
public interface IShortDescriptionHolder {
  /**
   * @return the short description of the current {@link IShortDescriptionHolder}
   */
  String getShortDescription();

  /**
   * @return the short description of the current {@link IShortDescriptionHolder}
   *         in single quotes
   */
  default String getShortDescriptionInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getShortDescription() + StringCatalog.SINGLE_QUOTE;
  }
}
