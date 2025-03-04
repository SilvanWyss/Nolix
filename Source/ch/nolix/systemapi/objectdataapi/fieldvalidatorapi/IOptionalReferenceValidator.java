package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.fieldapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;

public interface IOptionalReferenceValidator extends IFieldValidator {

  void assertCanClear(IOptionalReference<?> optionalReference);

  void assertCanSetGivenEntity(IOptionalReference<?> optionalReference, IEntity entity);

  void assertIsNotEmpty(IOptionalReference<?> optionalReference);
}
