/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalCardinalityHolder;

/**
 * A {@link MutableOptionalCardinalityHolder} is a
 * {@link OptionalCardinalityHolder} whose cardinality can be set and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableOptionalCardinalityHolder extends OptionalCardinalityHolder {
  /**
   * Removes the cardinality of the current
   * {@link MutableOptionalCardinalityHolder}.
   */
  void removeCardinality();

  /**
   * Sets the cardinality of the current
   * {@link MutableOptionalCardinalityHolder}.
   * 
   * @param cardinality
   * @throws RuntimeException if the given cardinality is null
   */
  void setCardinality(String cardinality);
}
