package ch.nolix.systemapi.objectdata.fieldtool;

import ch.nolix.systemapi.objectdata.model.IMultiValueField;

public interface IMultiValueFieldTool extends IFieldTool {

  boolean canAddValue(IMultiValueField<?> multiValueField);

  boolean canAddValue(IMultiValueField<?> multiValueField, Object value);

  boolean canClear(IMultiValueField<?> multiValueField);

  boolean canRemoveValue(IMultiValueField<?> multiValueField, Object value);
}
