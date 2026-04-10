/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.property.value;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the value of a {@link IMutableValue}.
 */
public interface IMutableValue<V> extends IBaseValue {
  /**
   * @return the value of the current {@link IMutableValue}.
   */
  V getStoredValue();

  /**
   * Sets the given value to the current {@link IMutableValue}.
   * 
   * @param value
   * @throws RuntimeException if the given value is null.
   */
  void setValue(V value);
}
