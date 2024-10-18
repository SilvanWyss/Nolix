package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;

public interface IOptionalReferenceValidator extends IFieldValidator {

  void assertCanClear(IOptionalReference<?> optionalReference);

  void assertCanSetGivenEntity(IOptionalReference<?> optionalReference, IEntity entity);

  void assertIsNotEmpty(IOptionalReference<?> optionalReference);
}
