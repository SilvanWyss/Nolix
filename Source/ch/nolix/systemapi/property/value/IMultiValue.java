/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.property.value;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <V> the type of the values of a {@link IValue}.
 */
public interface IMultiValue<V> extends Clearable, IBaseValue {
  /**
   * Adds the given value to the current {@link IMultiValue}.
   * 
   * @param value
   * @throws RuntimeException if the given value is null
   */
  void addValue(V value);

  /**
   * @return the values of the current {@link IMultiValue}.
   */
  ExtendedIterable<V> getStoredValues();

  /**
   * Removes all occurrences of the given value from the current
   * {@link IMultiValue}.
   * 
   * @param value
   */
  void removeAllOccurrencesOfValue(V value);
}
