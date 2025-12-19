package ch.nolix.coreapi.attribute.mutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalOneBasedIndexHolder;

/**
 * A {@link IMutableOptionalOneBasedIndexHolder} is a
 * {@link IOptionalOneBasedIndexHolder} whose one-based index can be set and
 * removed programmatically.
 * 
 * @author Silvan Wyss
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
