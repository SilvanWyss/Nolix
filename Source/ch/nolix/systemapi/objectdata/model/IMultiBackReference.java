/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link Entity}s a {@link IMultiBackReference} can
 *            reference back.
 */
public interface IMultiBackReference<E extends Entity> extends BaseBackReference, Iterable<E> {
  /**
   * @return the ids of all {@link Entity}s the current
   *         {@link IMultiBackReference} references back
   */
  ExtendedIterable<String> getAllBackReferencedEntityIds();

  /**
   * @return all {@link Entity}s the current {@link IMultiBackReference}
   *         references back
   */
  ExtendedIterable<E> getAllStoredBackReferencedEntities();

  /**
   * @return the new and deleted {@link IMultiBackReferenceEntry}s of the current
   *         {@link IMultiBackReference}
   */
  ExtendedIterable<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries();

  /**
   * @return true if the current {@link IMultiBackReference} has loaded the ids of
   *         all persisted back referenced {@link Entity}s, false otherwise
   */
  boolean loadedAllPersistedReferencedEntityIds();
}
