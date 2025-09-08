package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IMultiValueField;

public interface IMultiValueFieldValidator extends IFieldValidator {
  <V> void assertCanAddValue(IMultiValueField<V> multiValueField, V value);

  void assertCanClear(IMultiValueField<?> multiValueField);

  <V> void assertCanRemoveValue(IMultiValueField<V> multiValueField, V value);
}
