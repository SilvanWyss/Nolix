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
  <E extends Entity> void assertCanAddEntity(IMultiReference<E> multiReference, E entity);

  void assertCanBeCleared(IMultiReference<?> multiReference);

  <E extends Entity> void assertCanRemoveEntity(IMultiReference<E> multiReference, E entity);
}
