package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.fieldapi.IOptionalValue;

public interface IOptionalValueValidator extends IFieldValidator {

  <V> void assertCanSetGivenValue(IOptionalValue<V> optionalValue, V value);

  void assertHasValue(IOptionalValue<?> optionalValue);
}
