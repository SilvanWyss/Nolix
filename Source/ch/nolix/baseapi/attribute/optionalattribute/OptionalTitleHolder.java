/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

import ch.nolix.baseapi.commontype.stringtool.StringCatalog;

/**
 * A {@link OptionalTitleHolder} can have a title.
 * 
 * @author Silvan Wyss
 */
public interface OptionalTitleHolder {
  /**
   * @return the title of the current {@link OptionalTitleHolder}
   * @throws RuntimeException if the current {@link OptionalTitleHolder} does not
   *                          have a title
   */
  String getTitle();

  /**
   * @return the title of the current {@link OptionalTitleHolder} in single
   *         quotes
   * @throws RuntimeException if the current {@link OptionalTitleHolder} does not
   *                          have a title
   */
  default String getTitleInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getTitle() + StringCatalog.SINGLE_QUOTE;
  }

  /**
   * @return true if the current {@link OptionalTitleHolder} has a title, false
   *         otherwise
   */
  boolean hasTitle();
}
