/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

import ch.nolix.baseapi.commontypetool.stringtool.StringCatalog;

/**
 * A {@link IOptionalTitleHolder} can have a title.
 * 
 * @author Silvan Wyss
 */
public interface IOptionalTitleHolder {
  /**
   * @return the title of the current {@link IOptionalTitleHolder}
   * @throws RuntimeException if the current {@link IOptionalTitleHolder} does not
   *                          have a title
   */
  String getTitle();

  /**
   * @return the title of the current {@link IOptionalTitleHolder} in single
   *         quotes
   * @throws RuntimeException if the current {@link IOptionalTitleHolder} does not
   *                          have a title
   */
  default String getTitleInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getTitle() + StringCatalog.SINGLE_QUOTE;
  }

  /**
   * @return true if the current {@link IOptionalTitleHolder} has a title, false
   *         otherwise
   */
  boolean hasTitle();
}
