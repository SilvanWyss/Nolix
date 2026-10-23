/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

/**
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link IValueField}.
 */
public interface IValueField<V> extends BaseValueField<V> {
  /**
   * @return the value of the current {@link IValueField}
   * @throws RuntimeException if the current {@link IValueField} is empty
   */
  V getStoredValue();

  /**
   * Sets the given value to the current {@link IValueField}.
   *
   * @param value
   * @throws RuntimeException if the given value is null
   */
  void setValue(V value);

  /**
   * Sets the value the given string represents to the current
   * {@link IValueField}.
   *
   * @param string
   * @throws RuntimeException if the given string does not represent a value of
   *                          the value type of the current {@link IValueField}
   */
  void setValueFromString(String string);
}
