/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.ValueHolder;

/**
 * A {@link MutableValueHolder} is a {@link ValueHolder} whose value can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link MutableValueHolder}
 */
public interface MutableValueHolder<V> extends ValueHolder<V> {
  /**
   * Sets the value of the current {@link MutableValueHolder}.
   * 
   * @param value
   * @throws RuntimeException if the given value is null
   */
  void setValue(V value);
}
