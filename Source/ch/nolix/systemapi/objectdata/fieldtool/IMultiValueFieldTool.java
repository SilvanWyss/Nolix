package ch.nolix.systemapi.objectdata.fieldtool;

import ch.nolix.systemapi.objectdata.model.IMultiValueField;

public interface IMultiValueFieldTool extends IFieldTool {

  boolean canAddGivenValue(IMultiValueField<?> multiValue, Object value);

  boolean canClear(IMultiValueField<?> multiValue);

  <V> boolean canRemoveValue(IMultiValueField<V> multiValue, V value);
}
