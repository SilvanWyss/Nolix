package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IReference;

public interface IReferenceValidator extends IFieldValidator {

  void assertCanSetGivenEntity(IReference<?> reference, IEntity entity);
}
