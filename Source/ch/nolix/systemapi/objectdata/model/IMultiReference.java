/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import java.util.function.Predicate;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link Entity}s a {@link IMultiReference} can
 *            reference.
 */
public interface IMultiReference<E extends Entity> extends Clearable, BaseReference, Iterable<E> {
  /**
   * Adds the given entity to the current {@link IMultiReference}.
   *
   * @param entity
   * @throws RuntimeException if the given entity is null
   * @throws RuntimeException if the given entity is not a {@link Entity} the
   *                          current {@link IMultiReference} can reference
   */
  void addEntity(Object entity);

  /**
   * @return the ids of all {@link Entity}s the current {@link IMultiReference}
   *         references
   */
  ExtendedIterable<String> getAllReferencedEntityIds();

  /**
   * @return all {@link Entity}s the current {@link IMultiReference} references
   */
  ExtendedIterable<E> getAllStoredReferencedEntities();

  /**
   * @return the new and deleted {@link IMultiReferenceEntry}s of the current
   *         {@link IMultiReference}
   */
  ExtendedIterable<? extends IMultiReferenceEntry<E>> getStoredNewAndDeletedEntries();

  /**
   * @return true if the current {@link IMultiReference} has loaded the ids of all
   *         persisted referenced {@link Entity}s, false otherwise
   */
  boolean loadedAllPersistedReferencedEntityIds();

  /**
   * Removes the given entity from the current {@link IMultiReference}.
   *
   * @param entity
   * @throws RuntimeException if the given entity is null
   */
  void removeEntity(Object entity);

  /**
   * Removes the first {@link Entity} the given selector selects from the current
   * {@link IMultiReference} if there is such a {@link Entity}.
   *
   * @param selector
   */
  void removeFirstEntity(Predicate<E> selector);
}
