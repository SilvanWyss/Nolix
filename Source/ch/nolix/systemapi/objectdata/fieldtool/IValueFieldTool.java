package ch.nolix.systemapi.objectdata.fieldtool;

import ch.nolix.systemapi.objectdata.model.IValueField;

public interface IValueFieldTool extends IFieldTool {

  boolean canSetValue(IValueField<?> valueField);

  boolean canSetValue(IValueField<?> valueField, Object value);
}
