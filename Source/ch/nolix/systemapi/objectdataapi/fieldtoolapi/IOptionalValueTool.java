package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.fieldapi.IOptionalValue;

public interface IOptionalValueTool extends IFieldTool {

  boolean canSetGivenValue(IOptionalValue<?> optionalValue, Object value);
}
