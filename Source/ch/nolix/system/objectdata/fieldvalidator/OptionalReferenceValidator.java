//package declaration
package ch.nolix.system.objectdata.fieldvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldtool.OptionalReferenceTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IOptionalReferenceTool;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IOptionalReferenceValidator;

//class
public final class OptionalReferenceValidator extends FieldValidator implements IOptionalReferenceValidator {

  //constant
  private static final IOptionalReferenceTool OPTIONAL_REFERENCE_TOOL = new OptionalReferenceTool();

  //method
  @Override
  public void assertCanClear(final IOptionalReference<?> optionalReference) {
    if (!OPTIONAL_REFERENCE_TOOL.canClear(optionalReference)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(optionalReference, "cannot clear");
    }
  }

  //method
  @Override
  public void assertCanSetGivenEntity(final IOptionalReference<?> optionalReference, final IEntity entity) {
    if (!OPTIONAL_REFERENCE_TOOL.canSetGivenEntity(optionalReference, entity)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(optionalReference, "does not reference an entity");
    }
  }

  //method
  @Override
  public void assertIsNotEmpty(final IOptionalReference<?> optionalReference) {
    if (optionalReference.isEmpty()) {
      throw EmptyArgumentException.forArgument(optionalReference);
    }
  }
}
