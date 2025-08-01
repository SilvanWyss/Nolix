package ch.nolix.system.objectdata.fieldvalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.fieldtool.ValueFieldTool;
import ch.nolix.systemapi.objectdata.fieldtool.IValueFieldTool;
import ch.nolix.systemapi.objectdata.fieldvalidator.IValueValidator;
import ch.nolix.systemapi.objectdata.model.IValueField;

public final class ValueValidator extends FieldValidator implements IValueValidator {

  private static final IValueFieldTool VALUE_TOOL = new ValueFieldTool();

  @Override
  public void assertCanSetGivenValue(final IValueField<?> value, final Object valueToSet) {
    if (!VALUE_TOOL.canSetValue(value, valueToSet)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(value, "cannot set the given value");
    }
  }
}
