package ch.nolix.coreapi.attribute.mutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalCardinalityHolder;

/**
 * A {@link IMutableOptionalCardinalityHolder} is a
 * {@link IOptionalCardinalityHolder} whose cardinality can be set and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-10-24
 */
public interface IMutableOptionalCardinalityHolder extends IOptionalCardinalityHolder {
  /**
   * Removes the cardinality of the current
   * {@link IMutableOptionalCardinalityHolder}.
   */
  void removeCardinality();

  /**
   * Sets the cardinality of the current
   * {@link IMutableOptionalCardinalityHolder}.
   * 
   * @param cardinality
   * @throws RuntimeException if the given cardinality is null.
   */
  void setCardinality(String cardinality);
}
