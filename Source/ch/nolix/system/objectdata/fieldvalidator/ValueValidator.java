package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldexaminer.ValueFieldExaminer;
import ch.nolix.systemapi.objectdata.fieldexaminer.IValueFieldExaminer;
import ch.nolix.systemapi.objectdata.fieldvalidator.IValueValidator;
import ch.nolix.systemapi.objectdata.model.IValueField;

public final class ValueValidator extends FieldValidator implements IValueValidator {
  private static final IValueFieldExaminer VALUE_TOOL = new ValueFieldExaminer();

  @Override
  public void assertCanSetGivenValue(final IValueField<?> value, final Object valueToSet) {
    if (!VALUE_TOOL.canSetValue(value, valueToSet)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(value, "cannot set the given value");
    }
  }
}
