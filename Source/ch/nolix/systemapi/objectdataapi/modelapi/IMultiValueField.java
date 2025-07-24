package ch.nolix.systemapi.objectdataapi.modelapi;

import java.util.function.Predicate;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

public interface IMultiValueField<V> extends Clearable, IAbstractValueField<V> {

  void addValue(V value);

  IContainer<V> getAllStoredValues();

  IContainer<? extends IMultiValueFieldEntry<V>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedValues();

  void removeFirstValue(Predicate<V> selector);

  void removeValue(V value);
}
