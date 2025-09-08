package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldexaminer.MultiValueFieldTool;
import ch.nolix.systemapi.objectdata.fieldexaminer.IMultiValueFieldExaminer;
import ch.nolix.systemapi.objectdata.fieldvalidator.IMultiValueFieldValidator;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;

public final class MultiValueFieldValidator extends FieldValidator implements IMultiValueFieldValidator {
  private static final IMultiValueFieldExaminer MULTI_VALUE_EXAMINER = new MultiValueFieldTool();

  @Override
  public <V> void assertCanAddValue(final IMultiValueField<V> multiValueField, final V value) {
    if (!MULTI_VALUE_EXAMINER.canAddValue(multiValueField, value)) {
      throw //
      InvalidArgumentException.forArgumentAndErrorPredicate(
        multiValueField,
        "cannot add the given value '" + value + "'");
    }
  }

  @Override
  public void assertCanClear(final IMultiValueField<?> multiValueField) {
    if (!MULTI_VALUE_EXAMINER.canClear(multiValueField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(multiValueField, "cannot be cleared");
    }
  }

  @Override
  public <V> void assertCanRemoveValue(final IMultiValueField<V> multiValueField, final V value) {
    if (!MULTI_VALUE_EXAMINER.canRemoveValue(multiValueField, value)) {
      throw //
      InvalidArgumentException.forArgumentAndErrorPredicate(
        multiValueField,
        "cannot remove the given value '" + value + "'");
    }
  }
}
