package ch.nolix.systemapi.property.value;

import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link IOptionalValue}.
 */
public interface IOptionalValue<V> extends Clearable, IBaseValue {
  /**
   * @return the value of the current {@link IOptionalValue}
   * @throws RuntimeException if the current {@link IOptionalValue} does not
   *                          contain a value.
   */
  V getStoredValue();

  /**
   * Sets the given value to the current {@link IOptionalValue}.
   * 
   * @param value
   * @throws RuntimeException if the given value is null
   */
  void setValue(V value);
}
