/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import java.util.function.Predicate;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <E> the type of the {@link IEntity}s a {@link IMultiReference} can
 *            reference.
 */
public interface IMultiReference<E extends IEntity> extends Clearable, BaseReference, Iterable<E> {
  void addEntity(Object entity);

  ExtendedIterable<String> getAllReferencedEntityIds();

  ExtendedIterable<E> getAllStoredReferencedEntities();

  ExtendedIterable<? extends IMultiReferenceEntry<E>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedReferencedEntityIds();

  void removeEntity(Object entity);

  void removeFirstEntity(Predicate<E> selector);
}
