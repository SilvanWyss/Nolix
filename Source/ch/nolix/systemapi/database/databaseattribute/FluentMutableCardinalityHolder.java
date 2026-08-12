/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.database.databaseattribute;

import ch.nolix.systemapi.database.databaseproperty.Cardinality;

/**
 * A {@link FluentMutableCardinalityHolder} is a {@link CardinalityHolder} whose
 * {@link Cardinality} can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableCardinalityHolder}
 */
public interface FluentMutableCardinalityHolder<H extends FluentMutableCardinalityHolder<H>> extends CardinalityHolder {
  /**
   * Sets the {@link Cardinality} of the current
   * {@link FluentMutableCardinalityHolder}.
   * 
   * @param cardinality
   * @return the current {@link FluentMutableCardinalityHolder}
   * @throws RuntimeException if the given cardinality is null
   */
  H setCardinality(Cardinality cardinality);
}
