package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IValueField;

public interface IValueFieldTool extends IFieldTool {

  boolean canSetValue(IValueField<?> valueField);

  boolean canSetValue(IValueField<?> valueField, Object value);
}
