package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IValueField;

public interface IValueValidator extends IFieldValidator {
  void assertCanSetGivenValue(IValueField<?> value, Object valueToSet);
}
