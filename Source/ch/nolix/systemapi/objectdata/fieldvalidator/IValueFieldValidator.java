package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IValueField;

public interface IValueFieldValidator extends IFieldValidator {
  void assertCanSetValue(IValueField<?> valueField, Object value);
}
