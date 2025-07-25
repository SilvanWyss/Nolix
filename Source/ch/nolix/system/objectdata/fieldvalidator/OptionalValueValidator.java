package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldtool.OptionalValueTool;
import ch.nolix.systemapi.objectdata.fieldtool.IOptionalValueTool;
import ch.nolix.systemapi.objectdata.fieldvalidator.IOptionalValueValidator;
import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

public final class OptionalValueValidator extends FieldValidator implements IOptionalValueValidator {

  private static final IOptionalValueTool OPTIONAL_VALUE_TOOL = new OptionalValueTool();

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
