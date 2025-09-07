package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

public interface IOptionalValueFieldValidator extends IFieldValidator {
  <V> void assertCanSetValue(IOptionalValueField<V> optionalValueField, V value);

  void assertContainsValue(IOptionalValueField<?> optionalValueField);
}
