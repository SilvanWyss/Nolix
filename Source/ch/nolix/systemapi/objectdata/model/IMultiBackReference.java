package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the {@link IEntity}s a {@link IMultiBackReference}
 *            can reference back.
 */
public interface IMultiBackReference<E extends IEntity> extends IBaseBackReference, Iterable<E> {
  IContainer<String> getAllBackReferencedEntityIds();

  IContainer<E> getAllStoredBackReferencedEntities();

  IContainer<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedReferencedEntityIds();
}
