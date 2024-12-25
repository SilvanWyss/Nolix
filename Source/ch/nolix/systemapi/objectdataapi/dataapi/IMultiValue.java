package ch.nolix.systemapi.objectdataapi.dataapi;

import java.util.function.Predicate;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

public interface IMultiValue<V> extends Clearable, IAbstractValue<V> {

  void addValue(V value);

  IContainer<V> getAllStoredValues();

  IContainer<? extends IMultiValueEntry<V>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedValues();

  void removeFirstValue(Predicate<V> selector);

  void removeValue(V value);
}
