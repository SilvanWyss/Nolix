package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.fieldapi.IValue;

public interface IValueValidator extends IFieldValidator {

  void assertCanSetGivenValue(IValue<?> value, Object valueToSet);
}
