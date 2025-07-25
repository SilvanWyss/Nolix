package ch.nolix.coreapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.coreapi.datamodel.cardinality.ICardinalityHolder;

/**
 * A {@link IFluentMutableOptionalCardinalityHolder} is a
 * {@link ICardinalityHolder} whose cardinality can be set and removed
 * programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2023-10-23
 * @param <H> is the type of a {@link IFluentMutableOptionalCardinalityHolder}.
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
   * @return the current {@link IFluentMutableOptionalCardinalityHolder}.
   * @throws RuntimeException if the given cardinality is null.
   */
  H setCardinality(String cardinality);
}
