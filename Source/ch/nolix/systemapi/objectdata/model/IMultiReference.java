package ch.nolix.systemapi.objectdata.model;

import java.util.function.Predicate;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.state.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the {@link IEntity}s a {@link IMultiReference} can
 *            reference.
 */
public interface IMultiReference<E extends IEntity> extends Clearable, IBaseReference, Iterable<E> {
  void addEntity(Object entity);

  IContainer<String> getAllReferencedEntityIds();

  IContainer<E> getAllStoredReferencedEntities();

  IContainer<? extends IMultiReferenceEntry<E>> getStoredNewAndDeletedEntries();

  boolean loadedAllPersistedReferencedEntityIds();

  void removeEntity(Object entity);

  void removeFirstEntity(Predicate<E> selector);
}
