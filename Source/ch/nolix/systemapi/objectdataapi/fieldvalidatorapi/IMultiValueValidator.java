package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValueField;

public interface IMultiValueValidator extends IFieldValidator {

  void assertCanAddGivenValue(IMultiValueField<?> multiValue, Object value);

  void assertCanClear(IMultiValueField<?> multiValue);

  <V> void assertCanRemoveValue(IMultiValueField<V> multiValue, V value);
}
