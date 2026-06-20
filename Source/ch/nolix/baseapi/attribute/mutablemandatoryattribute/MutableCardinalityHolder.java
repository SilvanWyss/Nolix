/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.datamodel.cardinality.ICardinalityHolder;

/**
 * A {@link MutableCardinalityHolder} is a {@link ICardinalityHolder} whose
 * cardinality can be set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableCardinalityHolder extends ICardinalityHolder {
  /**
   * Sets the cardinality of the current {@link MutableCardinalityHolder}.
   * 
   * @param cardinality
   * @throws RuntimeException if the given cardinality is null
   */
  void setCardinality(String cardinality);
}
