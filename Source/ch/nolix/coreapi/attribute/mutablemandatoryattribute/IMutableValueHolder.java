/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.mutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IValueHolder;

/**
 * A {@link IMutableValueHolder} is a {@link IValueHolder} whose value can be
 * set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <V> is the value of a {@link IMutableValueHolder}.
 */
public interface IMutableValueHolder<V> extends IValueHolder<V> {
  /**
   * Sets the value of the current {@link IMutableValueHolder}.
   * 
   * @param value
   * @throws RuntimeException if the given value is null.
   */
  void setValue(V value);
}
