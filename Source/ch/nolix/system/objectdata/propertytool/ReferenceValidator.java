//package declaration
package ch.nolix.system.objectdata.propertytool;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.propertyvalidator.PropertyValidator;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IReferenceTool;
import ch.nolix.systemapi.objectdataapi.propertyvalidatorapi.IReferenceValidator;

//class
public class ReferenceValidator extends PropertyValidator implements IReferenceValidator {

  //constant
  private static final IReferenceTool REFERENCE_TOOL = new ReferenceTool();

  //method
  @Override
  public void assertCanSetGivenEntity(final IReference<?> reference, final IEntity entity) {
    if (!REFERENCE_TOOL.canSetGivenEntity(reference, entity)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(reference, "cannot reference the given entity");
    }
  }
}
