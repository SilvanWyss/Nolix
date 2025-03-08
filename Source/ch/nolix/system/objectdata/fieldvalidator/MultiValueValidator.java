package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldtool.MultiValueTool;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IMultiValueTool;
import ch.nolix.systemapi.objectdataapi.fieldvalidatorapi.IMultiValueValidator;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValue;

public final class MultiValueValidator extends FieldValidator implements IMultiValueValidator {

  private static final IMultiValueTool MULTI_VALUE_TOOL = new MultiValueTool();

  @Override
  public void assertCanAddGivenValue(final IMultiValue<?> multiValue, final Object value) {
    if (!MULTI_VALUE_TOOL.canAddGivenValue(multiValue, value)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiValue, "cannot add the given value");
    }
  }

  @Override
  public void assertCanClear(final IMultiValue<?> multiValue) {
    if (!MULTI_VALUE_TOOL.canClear(multiValue)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiValue, "cannot clear");
    }
  }

  @Override
  public <V> void assertCanRemoveValue(final IMultiValue<V> multiValue, final V value) {
    if (!MULTI_VALUE_TOOL.canRemoveValue(multiValue, value)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        multiValue,
        "cannot remove the given value '" + value + "'");
    }
  }
}
