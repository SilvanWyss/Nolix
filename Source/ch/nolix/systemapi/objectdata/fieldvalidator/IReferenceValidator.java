/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IReference;

/**
 * @author Silvan Wyss
 */
public interface IReferenceValidator extends IFieldValidator<IReference<IEntity>> {
  <E extends IEntity> void assertCanSetEntity(IReference<E> reference, E entity);
}
