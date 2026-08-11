package ch.nolix.systemapi.element.valueproperty;

import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link IOptionalValueProperty}.
 */
public interface IOptionalValueProperty<V> extends Clearable, BaseValueProperty {
  /**
   * @return the value of the current {@link IOptionalValueProperty}
   * @throws RuntimeException if the current {@link IOptionalValueProperty} does
   *                          not contain a value.
   */
  V getStoredValue();

  /**
   * Sets the given value to the current {@link IOptionalValueProperty}.
   * 
   * @param value
   * @throws RuntimeException if the given value is null
   */
  void setValue(V value);
}
