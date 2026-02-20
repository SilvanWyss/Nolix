/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.IOneBasedIndexHolder;

/**
 * A {@link IMutableOneBasedIndexHolder} is a {@link IOneBasedIndexHolder} whose
 * one-based index can be set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableOneBasedIndexHolder extends IOneBasedIndexHolder {
  /**
   * Sets the one-based index of the current {@link IOneBasedIndexHolder}.
   * 
   * @param oneBasedIndex
   */
  void setOneBasedIndex(int oneBasedIndex);
}
