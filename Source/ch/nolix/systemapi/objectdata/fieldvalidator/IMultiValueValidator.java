package ch.nolix.systemapi.objectdata.fieldvalidator;

import ch.nolix.systemapi.objectdata.model.IMultiValueField;

public interface IMultiValueValidator extends IFieldValidator {

  void assertCanAddGivenValue(IMultiValueField<?> multiValue, Object value);

  void assertCanClear(IMultiValueField<?> multiValue);

  <V> void assertCanRemoveValue(IMultiValueField<V> multiValue, V value);
}
