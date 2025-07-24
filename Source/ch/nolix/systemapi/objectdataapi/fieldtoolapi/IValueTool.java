package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IValueField;

public interface IValueTool extends IFieldTool {

  boolean canSetValue(IValueField<?> value, Object valueToSet);
}
