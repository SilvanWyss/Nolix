/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

import ch.nolix.baseapi.commontype.stringtool.StringCatalog;

/**
 * A {@link TitleHolder} has a title.
 * 
 * @author Silvan Wyss
 */
public interface TitleHolder {
  /**
   * @return the title of the current {@link TitleHolder}
   */
  String getTitle();

  /**
   * @return the title of the current {@link TitleHolder} in single quotes
   */
  default String getTitleInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getTitle() + StringCatalog.SINGLE_QUOTE;
  }
}
