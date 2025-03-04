package ch.nolix.systemapi.objectdataapi.fieldapi;

import java.util.function.Predicate;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractValue;

public interface IMultiValue<V> extends Clearable, IAbstractValue<V> {

  void addValue(V value);

  IContainer<V> getAllStoredValues();

  IContainer<? extends IMultiValueEntry<V>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedValues();

  void removeFirstValue(Predicate<V> selector);

  void removeValue(V value);
}
