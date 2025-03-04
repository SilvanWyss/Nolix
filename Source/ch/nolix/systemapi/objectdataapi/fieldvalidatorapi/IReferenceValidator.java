package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.fieldapi.IReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;

public interface IReferenceValidator extends IFieldValidator {

  void assertCanSetGivenEntity(IReference<?> reference, IEntity entity);
}
