package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

public interface IMultiReferenceValidator extends IFieldValidator {
  <E extends IEntity> void assertCanAddGivenEntity(IMultiReference<E> multiReference, E entity);

  void assertCanClear(IMultiReference<?> multiReference);

  <E extends IEntity> void assertCanRemoveEntity(IMultiReference<E> multiReference, E entity);
}
