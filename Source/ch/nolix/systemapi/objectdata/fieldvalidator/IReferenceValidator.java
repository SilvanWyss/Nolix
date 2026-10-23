/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.IReference;

/**
 * @author Silvan Wyss
 */
public interface IReferenceValidator extends IFieldValidator<IReference<Entity>> {
  /**
   * @param reference
   * @param entity
   * @param <E>       the type of the {@link Entity} the given reference can
   *                  reference
   * @throws RuntimeException if the given entity cannot be set to the given
   *                          reference
   */
  <E extends Entity> void assertCanSetEntity(IReference<E> reference, E entity);
}
