/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

/**
 * @author Silvan Wyss
 */
public interface IOptionalReferenceValidator extends IFieldValidator<IOptionalReference<Entity>> {
  /**
   * @param optionalReference
   * @throws RuntimeException if the given optionalReference cannot be cleared
   */
  void assertCanBeCleared(IOptionalReference<? extends Entity> optionalReference);

  /**
   * @param optionalReference
   * @param entity
   * @param <E>               the type of the {@link Entity} the given
   *                          optionalReference can reference
   * @throws RuntimeException if the given entity cannot be set to the given
   *                          optionalReference
   */
  <E extends Entity> void assertCanSetEntity(IOptionalReference<E> optionalReference, E entity);
}
