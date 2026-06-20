/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

/**
 * A {@link OptionalCardinalityHolder} can have a cardinality.
 * 
 * @author Silvan Wyss
 */
public interface OptionalCardinalityHolder {
  /**
   * @return the cardinality of the current {@link OptionalCardinalityHolder}
   * @throws RuntimeException if the current {@link OptionalCardinalityHolder}
   *                          does not have a cardinality
   */
  String getCardinality();

  /**
   * @return true if the current {@link OptionalCardinalityHolder} has a
   *         cardinality, false otherwise
   */
  boolean hasCardinality();
}
