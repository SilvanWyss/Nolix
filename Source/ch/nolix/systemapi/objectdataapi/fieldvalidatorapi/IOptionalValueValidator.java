package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalValueField;

public interface IOptionalValueValidator extends IFieldValidator {

  <V> void assertCanSetGivenValue(IOptionalValueField<V> optionalValue, V value);

  void assertHasValue(IOptionalValueField<?> optionalValue);
}
