package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IMultiBackReference<E extends IEntity> extends IAbstractBackReference<E> {

  IContainer<String> getAllBackReferencedEntityIds();

  IContainer<E> getAllStoredBackReferencedEntities();

  IContainer<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedReferencedEntityIds();
}
