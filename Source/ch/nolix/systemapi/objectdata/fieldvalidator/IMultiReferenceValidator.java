/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

/**
 * @author Silvan Wyss
 */
public interface IMultiReferenceValidator extends IFieldValidator<IMultiReference<Entity>> {
  /**
   * @param multiReference
   * @param entity
   * @param <E>            the type of the {@link Entity}s the given
   *                       multiReference can reference
   * @throws RuntimeException if the given multiReference cannot add the given
   *                          entity
   */
  <E extends Entity> void assertCanAddEntity(IMultiReference<E> multiReference, E entity);

  /**
   * @param multiReference
   * @throws RuntimeException if the given multiReference cannot be cleared
   */
  void assertCanBeCleared(IMultiReference<?> multiReference);

  /**
   * @param multiReference
   * @param entity
   * @param <E>            the type of the {@link Entity}s the given
   *                       multiReference can reference
   * @throws RuntimeException if the given multiReference cannot remove the given
   *                          entity
   */
  <E extends Entity> void assertCanRemoveEntity(IMultiReference<E> multiReference, E entity);
}
