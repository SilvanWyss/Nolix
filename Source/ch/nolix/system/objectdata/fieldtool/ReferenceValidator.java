//package declaration
package ch.nolix.system.objectdata.fieldtool;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldvalidator.FieldValidator;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IReferenceTool;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IReferenceValidator;

//class
public class ReferenceValidator extends FieldValidator implements IReferenceValidator {

  //constant
  private static final IReferenceTool REFERENCE_TOOL = new ReferenceTool();

  //method
  @Override
  public void assertCanSetGivenEntity(final IReference<?> reference, final IEntity entity) {
    if (!REFERENCE_TOOL.toReferenceCanSetEntity(reference, entity)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(reference, "cannot reference the given entity");
    }
  }
}
