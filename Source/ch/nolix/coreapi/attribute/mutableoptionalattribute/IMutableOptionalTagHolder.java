/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.mutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalTagHolder;

/**
 * A {@link IMutableOptionalTagHolder} is a {@link IOptionalTagHolder} whose tag
 * can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableOptionalTagHolder extends IOptionalTagHolder {
  /**
   * Removes the tag of the current {@link IMutableOptionalTagHolder}.
   */
  void removeTag();

  /**
   * Sets the tag of the current {@link IMutableOptionalTagHolder}.
   * 
   * @param tag
   * @throws RuntimeException if the given tag is null or blank.
   */
  void setTag(String tag);
}
