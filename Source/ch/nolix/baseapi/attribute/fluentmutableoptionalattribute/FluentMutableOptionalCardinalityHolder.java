/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.datamodel.cardinality.ICardinalityHolder;

/**
 * A {@link FluentMutableOptionalCardinalityHolder} is a
 * {@link ICardinalityHolder} whose cardinality can be set programmatically and
 * fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOptionalCardinalityHolder}
 */
public interface FluentMutableOptionalCardinalityHolder<H extends FluentMutableOptionalCardinalityHolder<H>>
extends ICardinalityHolder {
  /**
   * Removes the cardinality of the current
   * {@link FluentMutableOptionalCardinalityHolder}.
   */
  void removeCardinality();

  /**
   * Sets the cardinality of the current
   * {@link FluentMutableOptionalCardinalityHolder}.
   * 
   * @param cardinality
   * @return the current {@link FluentMutableOptionalCardinalityHolder}
   * @throws RuntimeException if the given cardinality is null
   */
  H setCardinality(String cardinality);
}
