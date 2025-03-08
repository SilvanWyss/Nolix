package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalReference;

public interface IOptionalReferenceValidator extends IFieldValidator {

  void assertCanClear(IOptionalReference<?> optionalReference);

  void assertCanSetGivenEntity(IOptionalReference<?> optionalReference, IEntity entity);

  void assertIsNotEmpty(IOptionalReference<?> optionalReference);
}
