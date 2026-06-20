/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.MultiValueHolder;

/**
 * A {@link MutableMultiValueHolder} is a {@link MultiValueHolder} whose
 * values can be added and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <V> the type of the values of a {@link MutableMultiValueHolder}
 */
public interface MutableMultiValueHolder<V> extends MultiValueHolder<V> {
  /**
   * Adds the given value to the current {@link MutableMultiValueHolder} if the
   * current {@link MutableMultiValueHolder} does not contain already the given
   * value.
   * 
   * @param value
   * @throws RuntimeException if the given value is null
   */
  void addValue(V value);

  /**
   * Removes the given value from the current {@link MutableMultiValueHolder} if
   * the current {@link MutableMultiValueHolder} contains the given value.
   * 
   * @param value
   */
  void removeValue(V value);

  /**
   * Removes all values from the current {@link MutableMultiValueHolder}.
   */
  void removeValues();
}
