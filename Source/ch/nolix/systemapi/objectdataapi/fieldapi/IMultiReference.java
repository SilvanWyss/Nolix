package ch.nolix.systemapi.objectdataapi.fieldapi;

import java.util.function.Predicate;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;

public interface IMultiReference<E extends IEntity> extends Clearable, IAbstractReference<E> {

  void addEntity(Object entity);

  IContainer<String> getAllReferencedEntityIds();

  IContainer<E> getAllStoredReferencedEntities();

  IContainer<? extends IMultiReferenceEntry<E>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedReferencedEntityIds();

  void removeEntity(Object entity);

  void removeFirstEntity(Predicate<E> selector);
}
