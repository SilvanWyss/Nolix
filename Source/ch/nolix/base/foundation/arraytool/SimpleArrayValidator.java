/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.foundation.arraytool;

import ch.nolix.baseapi.foundation.arraytool.IArrayValidator;

/**
 * @author Silvan Wyss
 */
public final class SimpleArrayValidator implements IArrayValidator {
  /**
   * {@inheritDoc}
   */
  @Override
  public void assertDoesNotContainNull(final Object[] array) {
    if (array == null) {
      throw new IllegalArgumentException("The given array is null.");
    }

    for (var i = 0; i < array.length; i++) {
      if (array[i] == null) {
        throw new IllegalArgumentException("The given array contains a null element at the index " + i + ".");
      }
    }
  }
}
