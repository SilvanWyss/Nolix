package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValueField;

public interface IMultiValueTool extends IFieldTool {

  boolean canAddGivenValue(IMultiValueField<?> multiValue, Object value);

  boolean canClear(IMultiValueField<?> multiValue);

  <V> boolean canRemoveValue(IMultiValueField<V> multiValue, V value);
}
