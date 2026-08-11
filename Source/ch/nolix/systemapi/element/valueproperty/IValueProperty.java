/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.valueproperty;

/**
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link IValueProperty}.
 */
public interface IValueProperty<V> extends BaseValueProperty {
  /**
   * @return the value of the current {@link IValueProperty}.
   */
  V getStoredValue();

  /**
   * Sets the given value to the current {@link IValueProperty}.
   * 
   * @param value
   * @throws RuntimeException if the given value is null
   */
  void setValue(V value);
}
