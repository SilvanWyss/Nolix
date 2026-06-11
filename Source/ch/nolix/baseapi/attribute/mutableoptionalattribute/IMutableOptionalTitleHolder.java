/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.IOptionalTitleHolder;

/**
 * A {@link IMutableOptionalTitleHolder} is a {@link IOptionalTitleHolder} whose
 * title can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableOptionalTitleHolder extends IOptionalTitleHolder {
  /**
   * Removes the title of the current {@link IMutableOptionalTitleHolder}.
   */
  void removeTitle();

  /**
   * Sets the title of the current {@link IMutableOptionalTitleHolder}.
   * 
   * @param title
   * @throws RuntimeException if the given title is null or blank
   */
  void setTitle(String title);
}
