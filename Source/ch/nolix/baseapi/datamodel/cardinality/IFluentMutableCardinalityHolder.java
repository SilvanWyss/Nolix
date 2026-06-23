/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datamodel.cardinality;

/**
 * A {@link IFluentMutableCardinalityHolder} is a {@link CardinalityHolder}
 * whose {@link Cardinality} can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> is the type of a {@link IFluentMutableCardinalityHolder}.
 */
public interface IFluentMutableCardinalityHolder<H extends IFluentMutableCardinalityHolder<H>>
extends CardinalityHolder {
  /**
   * Sets the {@link Cardinality} of the current
   * {@link IFluentMutableCardinalityHolder}.
   * 
   * @param cardinality
   * @return the current {@link IFluentMutableCardinalityHolder}.
   * @throws RuntimeException if the given cardinality is null.
   */
  H setCardinality(Cardinality cardinality);
}
