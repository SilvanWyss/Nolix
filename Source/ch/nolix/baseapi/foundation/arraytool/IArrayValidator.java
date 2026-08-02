/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.foundation.arraytool;

/**
 * @author Silvan Wyss
 */
public interface IArrayValidator {
  /**
   * @param array
   * @throws RuntimeException if the given array is null
   * @throws RuntimeException if the given array contains a null element
   */
  void assertDoesNotContainNull(Object[] array);
}
