package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IMultiReference;

public interface IMultiReferenceValidator extends IFieldValidator {

  void assertCanAddGivenEntity(IMultiReference<?> multiReference, IEntity entity);

  void assertCanClear(IMultiReference<?> multiReference);

  <E extends IEntity> void assertCanRemoveEntity(IMultiReference<E> multiReference, E entity);
}
