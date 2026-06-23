/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.datamodel.cardinality.CardinalityHolder;

/**
 * A {@link MutableCardinalityHolder} is a {@link CardinalityHolder} whose
 * cardinality can be set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableCardinalityHolder extends CardinalityHolder {
  /**
   * Sets the cardinality of the current {@link MutableCardinalityHolder}.
   * 
   * @param cardinality
   * @throws RuntimeException if the given cardinality is null
   */
  void setCardinality(String cardinality);
}
