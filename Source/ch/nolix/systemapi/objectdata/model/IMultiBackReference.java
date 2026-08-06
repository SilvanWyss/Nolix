/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link IEntity}s a {@link IMultiBackReference} can
 *            reference back.
 */
public interface IMultiBackReference<E extends IEntity> extends BaseBackReference, Iterable<E> {
  ExtendedIterable<String> getAllBackReferencedEntityIds();

  ExtendedIterable<E> getAllStoredBackReferencedEntities();

  ExtendedIterable<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedReferencedEntityIds();
}
