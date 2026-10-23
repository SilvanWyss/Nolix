/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

/**
 * @author Silvan Wyss
 */
public interface IOptionalReferenceExaminer extends IFieldExaminer<IOptionalReference<Entity>> {
  /**
   * @param optionalReference
   * @param <E>               the type of the {@link Entity} the given
   *                          optionalReference can reference
   * @return true if the given optionalReference can be cleared, false otherwise
   */
  <E extends Entity> boolean canBeCleared(IOptionalReference<E> optionalReference);

  /**
   * @param optionalReference
   * @param <E>               the type of the {@link Entity} the given
   *                          optionalReference can reference
   * @return true if a {@link Entity} can be set to the given optionalReference,
   *         false otherwise
   */
  <E extends Entity> boolean canSetEntity(IOptionalReference<E> optionalReference);

  /**
   * @param optionalReference
   * @param entity
   * @param <E>               the type of the {@link Entity} the given
   *                          optionalReference can reference
   * @return true if the given entity can be set to the given optionalReference,
   *         false otherwise
   */
  <E extends Entity> boolean canSetEntity(IOptionalReference<E> optionalReference, E entity);
}
