//package declaration
package ch.nolix.system.objectdatabase.propertyvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdatabase.propertytool.ValueTool;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IValue;
import ch.nolix.systemapi.objectdatabaseapi.propertytoolapi.IValueTool;
import ch.nolix.systemapi.objectdatabaseapi.propertyvalidatorapi.IValueValidator;

//class
public final class ValueValidator extends PropertyValidator implements IValueValidator {

  //constant
  private static final IValueTool VALUE_HELPER = new ValueTool();

  //method
  @Override
  public void assertCanSetGivenValue(final IValue<?> value, final Object valueToSet) {
    if (!VALUE_HELPER.canSetGivenValue(value, valueToSet)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(value, "cannot set the given value");
    }
  }
}
