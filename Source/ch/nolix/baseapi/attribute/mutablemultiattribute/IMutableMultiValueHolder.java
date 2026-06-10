/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.IMultiValueHolder;

/**
 * A {@link IMutableMultiValueHolder} is a {@link IMultiValueHolder} whose
 * values can be added and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <V> the type of the values of a {@link IMutableMultiValueHolder}
 */
public interface IMutableMultiValueHolder<V> extends IMultiValueHolder<V> {
  /**
   * Adds the given value to the current {@link IMutableMultiValueHolder} if the
   * current {@link IMutableMultiValueHolder} does not contain already the given
   * value.
   * 
   * @param value
   * @throws RuntimeException if the given value is null
   */
  void addValue(V value);

  /**
   * Removes the given value from the current {@link IMutableMultiValueHolder} if
   * the current {@link IMutableMultiValueHolder} contains the given value.
   * 
   * @param value
   */
  void removeValue(V value);

  /**
   * Removes all values from the current {@link IMutableMultiValueHolder}.
   */
  void removeValues();
}
