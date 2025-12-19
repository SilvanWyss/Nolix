package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldexaminer.ValueFieldExaminer;
import ch.nolix.systemapi.objectdata.fieldexaminer.IValueFieldExaminer;
import ch.nolix.systemapi.objectdata.fieldvalidator.IValueFieldValidator;
import ch.nolix.systemapi.objectdata.model.IValueField;

/**
 * @author Silvan Wyss
 */
public final class ValueFieldValidator extends FieldValidator implements IValueFieldValidator {
  private static final IValueFieldExaminer VALUE_TOOL = new ValueFieldExaminer();

  @Override
  public void assertCanSetValue(final IValueField<?> valueField, final Object value) {
    if (!VALUE_TOOL.canSetValue(valueField, value)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(valueField, "cannot set the given value");
    }
  }
}
