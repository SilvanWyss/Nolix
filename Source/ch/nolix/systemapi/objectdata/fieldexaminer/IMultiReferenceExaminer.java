/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

/**
 * @author Silvan Wyss
 */
public interface IMultiReferenceExaminer extends IFieldExaminer<IMultiReference<Entity>> {
  /**
   * @param multiReference
   * @param <E>            the type of the {@link Entity}s the given
   *                       multiReference can reference
   * @return true if the given multiReference can add a {@link Entity}, false
   *         otherwise
   */
  <E extends Entity> boolean canAddEntity(IMultiReference<E> multiReference);

  /**
   * @param multiReference
   * @param entity
   * @param <E>            the type of the {@link Entity}s the given
   *                       multiReference can reference
   * @return true if the given multiReference can add the given entity, false
   *         otherwise
   */
  <E extends Entity> boolean canAddEntity(IMultiReference<E> multiReference, E entity);

  /**
   * @param multiReference
   * @param <E>            the type of the {@link Entity}s the given
   *                       multiReference can reference
   * @return true if the given multiReference can be cleared, false otherwise
   */
  <E extends Entity> boolean canBeCleared(IMultiReference<E> multiReference);

  /**
   * @param multiReference
   * @param <E>            the type of the {@link Entity}s the given
   *                       multiReference can reference
   * @return true if the given multiReference can remove a {@link Entity}, false
   *         otherwise
   */
  <E extends Entity> boolean canRemoveEntity(IMultiReference<E> multiReference);

  /**
   * @param multiReference
   * @param entity
   * @param <E>            the type of the {@link Entity}s the given
   *                       multiReference can reference
   * @return true if the given multiReference can remove the given entity, false
   *         otherwise
   */
  <E extends Entity> boolean canRemoveEntity(IMultiReference<E> multiReference, E entity);
}
