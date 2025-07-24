package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IValueField;

public interface IValueValidator extends IFieldValidator {

  void assertCanSetGivenValue(IValueField<?> value, Object valueToSet);
}
