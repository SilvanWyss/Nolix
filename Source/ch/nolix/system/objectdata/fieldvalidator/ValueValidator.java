//package declaration
package ch.nolix.system.objectdata.fieldvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldtool.ValueTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IValueTool;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IValueValidator;

//class
public final class ValueValidator extends FieldValidator implements IValueValidator {

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
