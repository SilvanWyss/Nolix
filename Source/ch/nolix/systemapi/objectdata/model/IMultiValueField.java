/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import java.util.function.Predicate;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <V> the type of the values of a {@link IMultiValueField}.
 */
public interface IMultiValueField<V> extends Clearable, BaseValueField<V>, Iterable<V> {
  /**
   * Adds the given value to the current {@link IMultiValueField}.
   *
   * @param value
   * @throws RuntimeException if the given value is null
   */
  void addValue(V value);

  /**
   * @return all values of the current {@link IMultiValueField}
   */
  ExtendedIterable<V> getAllStoredValues();

  /**
   * @return the new and deleted {@link IMultiValueFieldEntry}s of the current
   *         {@link IMultiValueField}
   */
  ExtendedIterable<? extends IMultiValueFieldEntry<V>> getStoredNewAndDeletedEntries();

  /**
   * @return true if the current {@link IMultiValueField} has loaded all its
   *         persisted values, false otherwise
   */
  boolean loadedAllPersistedValues();

  /**
   * Removes the first value the given selector selects from the current
   * {@link IMultiValueField} if there is such a value.
   *
   * @param selector
   */
  void removeFirstValue(Predicate<V> selector);

  /**
   * Removes the given value from the current {@link IMultiValueField}.
   *
   * @param value
   * @throws RuntimeException if the given value is null
   */
  void removeValue(V value);
}
