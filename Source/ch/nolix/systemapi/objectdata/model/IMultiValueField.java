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
  void addValue(V value);

  ExtendedIterable<V> getAllStoredValues();

  ExtendedIterable<? extends IMultiValueFieldEntry<V>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedValues();

  void removeFirstValue(Predicate<V> selector);

  void removeValue(V value);
}
