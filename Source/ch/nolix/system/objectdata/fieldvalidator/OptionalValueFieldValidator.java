package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldexaminer.OptionalValueFieldExaminer;
import ch.nolix.systemapi.objectdata.fieldexaminer.IOptionalValueFieldExaminer;
import ch.nolix.systemapi.objectdata.fieldvalidator.IOptionalValueFieldValidator;
import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

public final class OptionalValueFieldValidator extends FieldValidator implements IOptionalValueFieldValidator {
  private static final IOptionalValueFieldExaminer OPTIONAL_VALUE_TOOL = new OptionalValueFieldExaminer();

  @Override
  public <V> void assertCanSetValue(final IOptionalValueField<V> optionalValueField, final V value) {
    if (!OPTIONAL_VALUE_TOOL.canSetValue(optionalValueField, value)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(optionalValueField, "cannot set the given value");
    }
  }

  @Override
  public void assertContainsValue(final IOptionalValueField<?> optionalValueField) {
    if (optionalValueField.isEmpty()) {
      throw EmptyArgumentException.forArgument(optionalValueField);
    }
  }
}
