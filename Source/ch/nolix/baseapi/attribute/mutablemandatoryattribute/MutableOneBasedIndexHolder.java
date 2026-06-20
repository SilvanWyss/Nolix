/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.OneBasedIndexHolder;

/**
 * A {@link MutableOneBasedIndexHolder} is a {@link OneBasedIndexHolder} whose
 * one-based index can be set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableOneBasedIndexHolder extends OneBasedIndexHolder {
  /**
   * Sets the one-based index of the current {@link OneBasedIndexHolder}.
   * 
   * @param oneBasedIndex
   */
  void setOneBasedIndex(int oneBasedIndex);
}
