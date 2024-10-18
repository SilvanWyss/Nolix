package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;

public interface IReferenceValidator extends IFieldValidator {

  void assertCanSetGivenEntity(IReference<?> reference, IEntity entity);
}
