/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.IMultiValueHolder;

/**
 * A {@link IFluentMutableMultiValueHolder} is a {@link IMultiValueHolder} whose
 * values can be added programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableMultiValueHolder}
 * @param <V> the type of the values of a {@link IFluentMutableMultiValueHolder}
 */
public interface IFluentMutableMultiValueHolder<H extends IFluentMutableMultiValueHolder<H, V>, V>
extends IMultiValueHolder<V> {
  /**
   * Adds the given value to the current {@link IFluentMutableMultiValueHolder} if
   * the current {@link IFluentMutableMultiValueHolder} does not contain already
   * the given value.
   * 
   * @param value
   * @return the current {@link IFluentMutableMultiValueHolder}
   * @throws RuntimeException if the given value is null
   */
  H addValue(V value);

  /**
   * Removes the given value from the current
   * {@link IFluentMutableMultiValueHolder} if the current
   * {@link IFluentMutableMultiValueHolder} contains the given value.
   * 
   * @param value
   */
  void removeValue(V value);

  /**
   * Removes all values from the current {@link IFluentMutableMultiValueHolder}.
   */
  void removeValues();
}
