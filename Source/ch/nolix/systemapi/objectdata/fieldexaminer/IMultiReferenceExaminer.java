/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

/**
 * @author Silvan Wyss
 */
public interface IMultiReferenceExaminer extends IFieldExaminer<IMultiReference<Entity>> {
  <E extends Entity> boolean canAddEntity(IMultiReference<E> multiReference);

  <E extends Entity> boolean canAddEntity(IMultiReference<E> multiReference, E entity);

  <E extends Entity> boolean canBeCleared(IMultiReference<E> multiReference);

  <E extends Entity> boolean canRemoveEntity(IMultiReference<E> multiReference);

  <E extends Entity> boolean canRemoveEntity(IMultiReference<E> multiReference, E entity);
}
