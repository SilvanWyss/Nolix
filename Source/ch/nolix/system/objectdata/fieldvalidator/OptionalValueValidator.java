package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldtool.OptionalValueTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IOptionalValueTool;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IOptionalValueValidator;

public final class OptionalValueValidator extends FieldValidator implements IOptionalValueValidator {

  private static final IOptionalValueTool OPTIONAL_VALUE_TOOL = new OptionalValueTool();

  @Override
  public <V> void assertCanSetGivenValue(final IOptionalValue<V> optionalValue, final V value) {
    if (!OPTIONAL_VALUE_TOOL.canSetGivenValue(optionalValue, value)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(optionalValue, "cannot set the given value");
    }
  }

  @Override
  public void assertHasValue(final IOptionalValue<?> optionalValue) {
    if (optionalValue.isEmpty()) {
      throw EmptyArgumentException.forArgument(optionalValue);
    }
  }
}
