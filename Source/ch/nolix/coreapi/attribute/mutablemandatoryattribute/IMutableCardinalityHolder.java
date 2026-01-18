/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.mutablemandatoryattribute;

import ch.nolix.coreapi.datamodel.cardinality.ICardinalityHolder;

/**
 * A {@link IMutableCardinalityHolder} is a {@link ICardinalityHolder} whose
 * cardinality can be set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableCardinalityHolder extends ICardinalityHolder {
  /**
   * Sets the cardinality of the current {@link IMutableCardinalityHolder}.
   * 
   * @param cardinality
   * @throws RuntimeException if the given cardinality is null.
   */
  void setCardinality(String cardinality);
}
