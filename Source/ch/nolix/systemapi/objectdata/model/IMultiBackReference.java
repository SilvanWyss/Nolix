package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface IMultiBackReference<E extends IEntity> extends IBaseBackReference, Iterable<E> {
  IContainer<String> getAllBackReferencedEntityIds();

  IContainer<E> getAllStoredBackReferencedEntities();

  IContainer<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedReferencedEntityIds();
}
