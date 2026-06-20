/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalTagHolder;

/**
 * A {@link MutableOptionalTagHolder} is a {@link OptionalTagHolder} whose tag
 * can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableOptionalTagHolder extends OptionalTagHolder {
  /**
   * Removes the tag of the current {@link MutableOptionalTagHolder}.
   */
  void removeTag();

  /**
   * Sets the tag of the current {@link MutableOptionalTagHolder}.
   * 
   * @param tag
   * @throws RuntimeException if the given tag is null or blank
   */
  void setTag(String tag);
}
