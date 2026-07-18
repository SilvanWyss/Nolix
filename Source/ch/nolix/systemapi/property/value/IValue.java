/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.property.value;

/**
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link IValue}.
 */
public interface IValue<V> extends IBaseValue {
  /**
   * @return the value of the current {@link IValue}.
   */
  V getStoredValue();

  /**
   * Sets the given value to the current {@link IValue}.
   * 
   * @param value
   * @throws RuntimeException if the given value is null
   */
  void setValue(V value);
}
