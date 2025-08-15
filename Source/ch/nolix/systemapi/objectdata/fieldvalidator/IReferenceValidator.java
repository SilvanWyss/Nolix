package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IReference;

public interface IReferenceValidator extends IFieldValidator {

  <E extends IEntity> void assertCanSetGivenEntity(IReference<E> reference, E entity);
}
