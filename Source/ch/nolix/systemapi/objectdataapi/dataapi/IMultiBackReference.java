package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IMultiBackReference<E extends IEntity> extends IBaseBackReference<E> {

  IContainer<String> getAllBackReferencedEntityIds();

  IContainer<E> getAllStoredBackReferencedEntities();

  IContainer<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedReferencedEntityIds();
}
