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
  <E extends Entity> boolean canSetEntity(IReference<E> reference);

  <E extends Entity> boolean canSetEntity(final IReference<E> reference, E entity);
}
