package ch.nolix.systemapi.objectdata.model;

import java.util.function.Predicate;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.state.statemutation.Clearable;

/**
 * @author Silvan Wyss
 */
public interface IMultiValueField<V> extends Clearable, IBaseValueField<V>, Iterable<V> {
  void addValue(V value);

  IContainer<V> getAllStoredValues();

  IContainer<? extends IMultiValueFieldEntry<V>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedValues();

  void removeFirstValue(Predicate<V> selector);

  void removeValue(V value);
}
