package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReference;

public interface IMultiReferenceValidator extends IFieldValidator {

  void assertCanAddGivenEntity(IMultiReference<?> multiReference, IEntity entity);

  void assertCanClear(IMultiReference<?> multiReference);

  <E extends IEntity> void assertCanRemoveEntity(IMultiReference<E> multiReference, E entity);
}
