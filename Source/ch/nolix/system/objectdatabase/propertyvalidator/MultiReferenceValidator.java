//package declaration
package ch.nolix.system.objectdatabase.propertyvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdatabase.propertyhelper.MultiReferenceHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiReference;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IMultiReferenceHelper;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IMultiReferenceValidator;

//class
public final class MultiReferenceValidator extends PropertyValidator implements IMultiReferenceValidator {

  //constant
  private static final IMultiReferenceHelper MULTI_REFERENCE_HELPER = new MultiReferenceHelper();

  //method
  @Override
  public void assertCanAddGivenEntity(final IMultiReference<?> multiReference, final IEntity entity) {
    if (!MULTI_REFERENCE_HELPER.canAddGivenEntity(multiReference, entity)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiReference, "cannot add the given entity");
    }
  }

  //method
  @Override
  public void assertCanClear(final IMultiReference<?> multiReference) {
    if (!MULTI_REFERENCE_HELPER.canClear(multiReference)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiReference, "cannot clear");
    }
  }

  //method
  @Override
  public <E extends IEntity> void assertCanRemoveEntity(
    final IMultiReference<E> multiReference,
    final E entity) {
    if (!MULTI_REFERENCE_HELPER.canRemoveEntity(multiReference, entity)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiReference, "cannot remove the given " + entity);
    }
  }
}
