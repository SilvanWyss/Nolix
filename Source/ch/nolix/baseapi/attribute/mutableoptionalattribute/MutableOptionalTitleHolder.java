/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalTitleHolder;

/**
 * A {@link MutableOptionalTitleHolder} is a {@link OptionalTitleHolder} whose
 * title can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableOptionalTitleHolder extends OptionalTitleHolder {
  /**
   * Removes the title of the current {@link MutableOptionalTitleHolder}.
   */
  void removeTitle();

  /**
   * Sets the title of the current {@link MutableOptionalTitleHolder}.
   * 
   * @param title
   * @throws RuntimeException if the given title is null or blank
   */
  void setTitle(String title);
}
