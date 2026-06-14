/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the {@link IEntity}s a {@link IMultiBackReference}
 *            can reference back.
 */
public interface IMultiBackReference<E extends IEntity> extends IBaseBackReference, Iterable<E> {
  IWellOrderContainer<String> getAllBackReferencedEntityIds();

  IWellOrderContainer<E> getAllStoredBackReferencedEntities();

  IWellOrderContainer<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedReferencedEntityIds();
}
