/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

/**
 * @author Silvan Wyss
 */
public interface IMultiReferenceValidator extends IFieldValidator {
  <E extends IEntity> void assertCanAddEntity(IMultiReference<E> multiReference, E entity);

  void assertCanBeCleared(IMultiReference<?> multiReference);

  <E extends IEntity> void assertCanRemoveEntity(IMultiReference<E> multiReference, E entity);
}
