/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import java.util.function.Predicate;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.state.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the values of a {@link IMultiValueField}.
 */
public interface IMultiValueField<V> extends Clearable, IBaseValueField<V>, Iterable<V> {
  void addValue(V value);

  IWellOrderContainer<V> getAllStoredValues();

  IWellOrderContainer<? extends IMultiValueFieldEntry<V>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedValues();

  void removeFirstValue(Predicate<V> selector);

  void removeValue(V value);
}
