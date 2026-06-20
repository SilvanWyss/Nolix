/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

/**
 * A {@link OptionalOneBasedIndexHolder} can have a one-based index.
 * 
 * @author Silvan Wyss
 */
public interface OptionalOneBasedIndexHolder {
  /**
   * @return the one-based index of the current
   *         {@link OptionalOneBasedIndexHolder}
   * @throws RuntimeException if the current {@link OptionalOneBasedIndexHolder}
   *                          does not have a one-based index
   */
  int getOneBasedIndex();

  /**
   * @return true if the current {@link OptionalOneBasedIndexHolder} has a
   *         one-based index, false otherwise
   */
  boolean hasOneBasedIndex();
}
