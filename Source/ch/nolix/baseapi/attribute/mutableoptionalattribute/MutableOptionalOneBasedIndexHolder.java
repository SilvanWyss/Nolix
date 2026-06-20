/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalOneBasedIndexHolder;

/**
 * A {@link MutableOptionalOneBasedIndexHolder} is a
 * {@link OptionalOneBasedIndexHolder} whose one-based index can be set and
 * removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableOptionalOneBasedIndexHolder extends OptionalOneBasedIndexHolder {
  /**
   * Removes the one-based index of the current
   * {@link MutableOptionalOneBasedIndexHolder}.
   */
  void removeOneBasedIndex();

  /**
   * Sets the one-based index of the current
   * {@link MutableOptionalOneBasedIndexHolder}.
   * 
   * @param oneBasedIndex
   */
  void setOneBasedIndex(int oneBasedIndex);
}
