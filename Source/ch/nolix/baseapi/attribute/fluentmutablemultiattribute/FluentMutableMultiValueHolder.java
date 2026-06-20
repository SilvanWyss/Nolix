/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.MultiValueHolder;

/**
 * A {@link FluentMutableMultiValueHolder} is a {@link MultiValueHolder} whose
 * values can be added programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableMultiValueHolder}
 * @param <V> the type of the values of a {@link FluentMutableMultiValueHolder}
 */
public interface FluentMutableMultiValueHolder<H extends FluentMutableMultiValueHolder<H, V>, V>
extends MultiValueHolder<V> {
  /**
   * Adds the given value to the current {@link FluentMutableMultiValueHolder} if
   * the current {@link FluentMutableMultiValueHolder} does not contain already
   * the given value.
   * 
   * @param value
   * @return the current {@link FluentMutableMultiValueHolder}
   * @throws RuntimeException if the given value is null
   */
  H addValue(V value);

  /**
   * Removes the given value from the current
   * {@link FluentMutableMultiValueHolder} if the current
   * {@link FluentMutableMultiValueHolder} contains the given value.
   * 
   * @param value
   */
  void removeValue(V value);

  /**
   * Removes all values from the current {@link FluentMutableMultiValueHolder}.
   */
  void removeValues();
}
