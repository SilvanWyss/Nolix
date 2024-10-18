package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldtool.ValueTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IValueTool;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IValueValidator;

public final class ValueValidator extends FieldValidator implements IValueValidator {

  private static final IValueTool VALUE_TOOL = new ValueTool();

  @Override
  public void assertCanSetGivenValue(final IValue<?> value, final Object valueToSet) {
    if (!VALUE_TOOL.canSetValue(value, valueToSet)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(value, "cannot set the given value");
    }
  }
}
