package ch.nolix.coreapi.attributeapi.fluentmutablemultiattributeapi;

import ch.nolix.coreapi.attributeapi.multiattributeapi.IMultiValueHolder;

/**
 * A {@link IFluentMutableMultiValueHolder} is a {@link IMultiValueHolder} whose
 * values can be added and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @version 2023-08-25
 * @param <H> is the type of a {@link IFluentMutableMultiValueHolder}.
 * @param <V> is the type of the values of a
 *            {@link IFluentMutableMultiValueHolder}.
 */
public interface IFluentMutableMultiValueHolder<H extends IFluentMutableMultiValueHolder<H, V>, V>
extends IMultiValueHolder<V> {

  /**
   * Adds the given value to the current {@link IFluentMutableMultiValueHolder}.
   * 
   * @param value
   * @return the current {@link IFluentMutableMultiValueHolder}.
   * @throws RuntimeException if the given value is null.
   */
  H addValue(V value);

  /**
   * Removes the given value from the current
   * {@link IFluentMutableMultiValueHolder}.
   * 
   * @param value
   * @throws RuntimeException if the current
   *                          {@link IFluentMutableMultiValueHolder} does not
   *                          contain the given value.
   */
  void removeValue(V value);

  /**
   * Removes all values from the current {@link IFluentMutableMultiValueHolder}.
   */
  void removeValues();
}
