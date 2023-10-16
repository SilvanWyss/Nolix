//package declaration
package ch.nolix.system.objectdatabase.propertyvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdatabase.propertyhelper.OptionalReferenceHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalReference;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IOptionalReferenceHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IOptionalReferenceValidator;

//class
public final class OptionalReferenceValidator extends PropertyValidator implements IOptionalReferenceValidator {

  //constant
  private static final IOptionalReferenceHelper OPTIONAL_REFERENCE_HELPER = new OptionalReferenceHelper();

  //method
  @Override
  public void assertCanClear(final IOptionalReference<?> optionalReference) {
    if (!OPTIONAL_REFERENCE_HELPER.canClear(optionalReference)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(optionalReference, "cannot clear");
    }
  }

  //method
  @Override
  public void assertCanSetGivenEntity(final IOptionalReference<?> optionalReference, final IEntity entity) {
    if (!OPTIONAL_REFERENCE_HELPER.canSetGivenEntity(optionalReference, entity)) {
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
