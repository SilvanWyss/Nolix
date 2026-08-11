/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.valueproperty;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <V> the type of the values of a {@link IValueProperty}.
 */
public interface IMultiValueProperty<V> extends Clearable, BaseValueProperty {
  /**
   * Adds the given value to the current {@link IMultiValueProperty}.
   * 
   * @param value
   * @throws RuntimeException if the given value is null
   */
  void addValue(V value);

  /**
   * @return the values of the current {@link IMultiValueProperty}.
   */
  ExtendedIterable<V> getStoredValues();

  /**
   * Removes all occurrences of the given value from the current
   * {@link IMultiValueProperty}.
   * 
   * @param value
   */
  void removeAllOccurrencesOfValue(V value);
}
