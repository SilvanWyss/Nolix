/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import java.util.function.Predicate;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.state.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the {@link IEntity}s a {@link IMultiReference} can
 *            reference.
 */
public interface IMultiReference<E extends IEntity> extends Clearable, IBaseReference, Iterable<E> {
  void addEntity(Object entity);

  IWellOrderContainer<String> getAllReferencedEntityIds();

  IWellOrderContainer<E> getAllStoredReferencedEntities();

  IWellOrderContainer<? extends IMultiReferenceEntry<E>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedReferencedEntityIds();

  void removeEntity(Object entity);

  void removeFirstEntity(Predicate<E> selector);
}
