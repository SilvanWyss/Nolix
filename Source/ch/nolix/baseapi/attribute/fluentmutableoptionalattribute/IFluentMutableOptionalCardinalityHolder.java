/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.datamodel.cardinality.ICardinalityHolder;

/**
 * A {@link IFluentMutableOptionalCardinalityHolder} is a
 * {@link ICardinalityHolder} whose cardinality can be set programmatically and
 * fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableOptionalCardinalityHolder}
 */
public interface IFluentMutableOptionalCardinalityHolder<H extends IFluentMutableOptionalCardinalityHolder<H>>
extends ICardinalityHolder {
  /**
   * Removes the cardinality of the current
   * {@link IFluentMutableOptionalCardinalityHolder}.
   */
  void removeCardinality();

  /**
   * Sets the cardinality of the current
   * {@link IFluentMutableOptionalCardinalityHolder}.
   * 
   * @param cardinality
   * @return the current {@link IFluentMutableOptionalCardinalityHolder}
   * @throws RuntimeException if the given cardinality is null
   */
  H setCardinality(String cardinality);
}
