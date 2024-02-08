//package declaration
package ch.nolix.system.objectdata.propertyvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.propertytool.ValueTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IValueTool;
import ch.nolix.systemapi.objectdataapi.propertyvalidatorapi.IValueValidator;

//class
public final class ValueValidator extends PropertyValidator implements IValueValidator {

  //constant
  private static final IValueTool VALUE_TOOL = new ValueTool();

  //method
  @Override
  public void assertCanSetGivenValue(final IValue<?> value, final Object valueToSet) {
    if (!VALUE_TOOL.canSetGivenValue(value, valueToSet)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(value, "cannot set the given value");
    }
  }
}