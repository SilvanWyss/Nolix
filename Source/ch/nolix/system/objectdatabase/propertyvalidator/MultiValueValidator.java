//package declaration
package ch.nolix.system.objectdatabase.propertyvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdatabase.propertytool.MultiValueTool;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.objectdatabaseapi.propertytoolapi.IMultiValueTool;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IMultiValueValidator;

//class
public final class MultiValueValidator extends PropertyValidator implements IMultiValueValidator {

  //constant
  private static final IMultiValueTool MULTI_VALUE_HELPER = new MultiValueTool();

  //method
  @Override
  public void assertCanAddGivenValue(final IMultiValue<?> multiValue, final Object value) {
    if (!MULTI_VALUE_HELPER.canAddGivenValue(multiValue, value)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiValue, "cannot add the given value");
    }
  }

  //method
  @Override
  public void assertCanClear(final IMultiValue<?> multiValue) {
    if (!MULTI_VALUE_HELPER.canClear(multiValue)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiValue, "cannot clear");
    }
  }

  //method
  @Override
  public <V> void assertCanRemoveValue(final IMultiValue<V> multiValue, final V value) {
    if (!MULTI_VALUE_HELPER.canRemoveValue(multiValue, value)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        multiValue,
        "cannot remove the given value '" + value + "'");
    }
  }
}
