package ch.nolix.systemapi.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

/**
 * @author Silvan Wyss
 */
public interface IMultiReferenceExaminer extends IFieldExaminer {
  <E extends IEntity> boolean canAddEntity(IMultiReference<E> multiReference);

  <E extends IEntity> boolean canAddEntity(IMultiReference<E> multiReference, E entity);

  <E extends IEntity> boolean canBeCleared(IMultiReference<E> multiReference);

  <E extends IEntity> boolean canRemoveEntity(IMultiReference<E> multiReference);

  <E extends IEntity> boolean canRemoveEntity(IMultiReference<E> multiReference, E entity);
}
