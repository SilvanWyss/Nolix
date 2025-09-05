package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldexaminer.ReferenceExaminer;
import ch.nolix.systemapi.objectdata.fieldexaminer.IReferenceExaminer;
import ch.nolix.systemapi.objectdata.fieldvalidator.IReferenceValidator;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IReference;

public class ReferenceValidator extends FieldValidator implements IReferenceValidator {
  private static final IReferenceExaminer REFERENCE_EXAMINER = new ReferenceExaminer();

  @Override
  public <E extends IEntity> void assertCanSetGivenEntity(final IReference<E> reference, final E entity) {
    if (!REFERENCE_EXAMINER.canSetEntity(reference, entity)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(reference, "cannot reference the given entity");
    }
  }
}
