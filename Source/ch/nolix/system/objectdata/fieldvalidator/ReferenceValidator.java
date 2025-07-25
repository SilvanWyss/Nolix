package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldtool.ReferenceTool;
import ch.nolix.systemapi.objectdata.fieldtool.IReferenceTool;
import ch.nolix.systemapi.objectdata.fieldvalidator.IReferenceValidator;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IReference;

public class ReferenceValidator extends FieldValidator implements IReferenceValidator {

  private static final IReferenceTool REFERENCE_TOOL = new ReferenceTool();

  @Override
  public void assertCanSetGivenEntity(final IReference<?> reference, final IEntity entity) {
    if (!REFERENCE_TOOL.toReferenceCanSetEntity(reference, entity)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(reference, "cannot reference the given entity");
    }
  }
}
