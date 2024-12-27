package ch.nolix.systemapi.objectdataapi.modelapi;

import java.util.function.Predicate;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

public interface IMultiReference<E extends IEntity> extends Clearable, IAbstractReference<E> {

  void addEntity(Object entity);

  IContainer<String> getAllReferencedEntityIds();

  IContainer<E> getAllStoredReferencedEntities();

  IContainer<? extends IMultiReferenceEntry<E>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedReferencedEntityIds();

  void removeEntity(Object entity);

  void removeFirstEntity(Predicate<E> selector);
}
