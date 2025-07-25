package ch.nolix.coreapi.attribute.mutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IOneBasedIndexHolder;

/**
 * A {@link IMutableOneBasedIndexHolder} is a {@link IOneBasedIndexHolder} whose
 * one-based index can be set programmatically.
 * 
 * @author Silvan Wyss
 * @version 2025-06-01
 */
public interface IMutableOneBasedIndexHolder extends IOneBasedIndexHolder {

  /**
   * Sets the one-based index of the current {@link IOneBasedIndexHolder}.
   * 
   * @param oneBasedIndex
   */
  void setOneBasedIndex(int oneBasedIndex);
}
