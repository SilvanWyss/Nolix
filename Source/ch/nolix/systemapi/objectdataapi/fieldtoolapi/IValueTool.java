package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IValue;

public interface IValueTool extends IFieldTool {

  boolean canSetValue(IValue<?> value, Object valueToSet);
}
