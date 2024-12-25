package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;

public interface IOptionalValueTool extends IFieldTool {

  boolean canSetGivenValue(IOptionalValue<?> optionalValue, Object value);
}
