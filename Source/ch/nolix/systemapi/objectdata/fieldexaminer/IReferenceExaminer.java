/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.IReference;

/**
 * @author Silvan Wyss
 */
public interface IReferenceExaminer extends IFieldExaminer<IReference<Entity>> {
  /**
   * @param reference
   * @param <E>       the type of the {@link Entity} the given reference can
   *                  reference
   * @return true if a {@link Entity} can be set to the given reference, false
   *         otherwise
   */
  <E extends Entity> boolean canSetEntity(IReference<E> reference);

  /**
   * @param reference
   * @param entity
   * @param <E>       the type of the {@link Entity} the given reference can
   *                  reference
   * @return true if the given entity can be set to the given reference, false
   *         otherwise
   */
  <E extends Entity> boolean canSetEntity(IReference<E> reference, E entity);
}
