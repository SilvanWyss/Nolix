/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link IOptionalValueField}.
 */
public interface IOptionalValueField<V> extends Clearable, BaseValueField<V> {
  /**
   * @return the value of the current {@link IOptionalValueField}
   * @throws RuntimeException if the current {@link IOptionalValueField} is empty
   */
  V getStoredValue();

  /**
   * Sets the given value to the current {@link IOptionalValueField}.
   *
   * @param value
   * @throws RuntimeException if the given value is null
   */
  void setValue(V value);

  /**
   * Sets the value the given string represents to the current
   * {@link IOptionalValueField}.
   *
   * @param string
   * @throws RuntimeException if the given string does not represent a value of
   *                          the value type of the current
   *                          {@link IOptionalValueField}
   */
  void setValueFromString(String string);
}
