package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalValueField;

public interface IOptionalValueValidator extends IFieldValidator {

  <V> void assertCanSetValue(IOptionalValueField<V> optionalValueField, V value);

  void assertContainsValue(IOptionalValueField<?> optionalValueField);
}
