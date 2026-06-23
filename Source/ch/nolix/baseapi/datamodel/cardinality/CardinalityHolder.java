/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datamodel.cardinality;

/**
 * A {@link CardinalityHolder} has a {@link Cardinality}.
 * 
 * @author Silvan Wyss
 */
public interface CardinalityHolder {
  /**
   * @return the {@link Cardinality} of the current {@link CardinalityHolder}
   */
  Cardinality getCardinality();

  /**
   * @return the {@link BaseCardinality} of the {@link Cardinality} of the current
   *         {@link CardinalityHolder}
   */
  default BaseCardinality getBaseCardinality() {
    return getCardinality().getBaseCardinality();
  }
}
