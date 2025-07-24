package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalValueField;

public interface IOptionalValueTool extends IFieldTool {

  boolean canSetGivenValue(IOptionalValueField<?> optionalValue, Object value);
}
