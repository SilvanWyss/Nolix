package ch.nolix.systemapi.objectdataapi.fieldtoolapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalValueField;

public interface IOptionalValueTool extends IFieldTool {

  boolean canSetValue(IOptionalValueField<?> optionalValueField);

  boolean canSetValue(IOptionalValueField<?> optionalValueField, Object value);
}
