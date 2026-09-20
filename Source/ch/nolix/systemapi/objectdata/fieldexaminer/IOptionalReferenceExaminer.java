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
  <E extends Entity> boolean canBeCleared(IOptionalReference<E> optionalReference);

  <E extends Entity> boolean canSetEntity(IOptionalReference<E> optionalReference);

  <E extends Entity> boolean canSetEntity(IOptionalReference<E> optionalReference, E entity);
}
