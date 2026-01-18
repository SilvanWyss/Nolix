/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.optionalattribute;

/**
 * A {@link IOptionalTitleHolder} can have a title.
 * 
 * @author Silvan Wyss
 */
public interface IOptionalTitleHolder {
  /**
   * @return the title of the current {@link IOptionalTitleHolder}.
   * @throws RuntimeException if the current {@link IOptionalTitleHolder} does not
   *                          have a title.
   */
  String getTitle();

  /**
   * @return the title of the current {@link IOptionalTitleHolder} in quotes.
   * @throws RuntimeException if the current {@link IOptionalTitleHolder} does not
   *                          have a title.
   */
  default String getTitleInQuotes() {
    return ("'" + getTitle() + "'");
  }

  /**
   * @return true if the current {@link IOptionalTitleHolder} has a title, false
   *         otherwise.
   */
  boolean hasTitle();
}
