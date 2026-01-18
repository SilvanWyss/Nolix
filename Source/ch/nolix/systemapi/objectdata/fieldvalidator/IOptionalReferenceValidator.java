/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

/**
 * @author Silvan Wyss
 */
public interface IOptionalReferenceValidator extends IFieldValidator {
  void assertCanBeCleared(IOptionalReference<?> optionalReference);

  <E extends IEntity> void assertCanSetEntity(IOptionalReference<E> optionalReference, E entity);

  void assertIsNotEmpty(IOptionalReference<?> optionalReference);
}
