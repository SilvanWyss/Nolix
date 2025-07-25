package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.container.base.IContainer;

public interface IMultiBackReference<E extends IEntity> extends IAbstractBackReference<E> {

  IContainer<String> getAllBackReferencedEntityIds();

  IContainer<E> getAllStoredBackReferencedEntities();

  IContainer<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedReferencedEntityIds();
}
