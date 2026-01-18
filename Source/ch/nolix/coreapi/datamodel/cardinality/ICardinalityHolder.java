/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.datamodel.cardinality;

/**
 * A {@link ICardinalityHolder} has a {@link Cardinality}.
 * 
 * @author Silvan Wyss
 */
public interface ICardinalityHolder {
  /**
   * @return the {@link Cardinality} of the current {@link ICardinalityHolder}.
   */
  Cardinality getCardinality();

  /**
   * @return the {@link BaseCardinality} of the current
   *         {@link ICardinalityHolder}.
   */
  default BaseCardinality getBaseCardinality() {
    return getCardinality().getBaseCardinality();
  }
}
