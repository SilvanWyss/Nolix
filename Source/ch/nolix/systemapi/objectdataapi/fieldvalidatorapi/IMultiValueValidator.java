package ch.nolix.systemapi.objectdataapi.fieldvalidatorapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;

public interface IMultiValueValidator extends IFieldValidator {

  void assertCanAddGivenValue(IMultiValue<?> multiValue, Object value);

  void assertCanClear(IMultiValue<?> multiValue);

  <V> void assertCanRemoveValue(IMultiValue<V> multiValue, V value);
}
