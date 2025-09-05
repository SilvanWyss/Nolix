package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldexaminer.MultiValueFieldTool;
import ch.nolix.systemapi.objectdata.fieldexaminer.IMultiValueFieldExaminer;
import ch.nolix.systemapi.objectdata.fieldvalidator.IMultiValueValidator;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;

public final class MultiValueValidator extends FieldValidator implements IMultiValueValidator {
  private static final IMultiValueFieldExaminer MULTI_VALUE_TOOL = new MultiValueFieldTool();

  @Override
  public void assertCanAddGivenValue(final IMultiValueField<?> multiValue, final Object value) {
    if (!MULTI_VALUE_TOOL.canAddValue(multiValue, value)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiValue, "cannot add the given value");
    }
  }

  @Override
  public void assertCanClear(final IMultiValueField<?> multiValue) {
    if (!MULTI_VALUE_TOOL.canClear(multiValue)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiValue, "cannot clear");
    }
  }

  @Override
  public <V> void assertCanRemoveValue(final IMultiValueField<V> multiValue, final V value) {
    if (!MULTI_VALUE_TOOL.canRemoveValue(multiValue, value)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        multiValue,
        "cannot remove the given value '" + value + "'");
    }
  }
}
