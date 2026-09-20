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
  void assertCanBeCleared(IOptionalReference<? extends Entity> optionalReference);

  <E extends Entity> void assertCanSetEntity(IOptionalReference<E> optionalReference, E entity);
}
