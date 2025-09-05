package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IOptionalReference;

public interface IOptionalReferenceValidator extends IFieldValidator {
  void assertCanClear(IOptionalReference<?> optionalReference);

  <E extends IEntity> void assertCanSetGivenEntity(IOptionalReference<E> optionalReference, E entity);

  void assertIsNotEmpty(IOptionalReference<?> optionalReference);
}
