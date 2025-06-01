package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalOneBasedIndexHolder;

/**
 * A {@link IMutableOptionalOneBasedIndexHolder} is a
 * {@link IOptionalOneBasedIndexHolder} whose one-based index can be set and
 * removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2025-06-01
 */
public interface IMutableOptionalOneBasedIndexHolder extends IOptionalOneBasedIndexHolder {

  /**
   * Removes the one-based index of the current
   * {@link IMutableOptionalOneBasedIndexHolder}.
   */
  void removeOneBasedIndex();

  /**
   * Sets the one-based index of the current
   * {@link IMutableOptionalOneBasedIndexHolder}.
   * 
   * @param oneBasedIndex
   */
  void setOneBasedIndex(int oneBasedIndex);
}
