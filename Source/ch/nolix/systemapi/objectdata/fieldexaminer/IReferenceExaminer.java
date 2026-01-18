/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IReference;

/**
 * @author Silvan Wyss
 */
public interface IReferenceExaminer extends IFieldExaminer {
  <E extends IEntity> boolean canSetEntity(IReference<E> reference);

  <E extends IEntity> boolean canSetEntity(final IReference<E> reference, E entity);
}
