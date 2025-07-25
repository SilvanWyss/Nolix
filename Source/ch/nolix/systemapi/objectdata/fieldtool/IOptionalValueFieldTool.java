package ch.nolix.systemapi.objectdata.fieldtool;

import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

public interface IOptionalValueFieldTool extends IFieldTool {

  boolean canSetValue(IOptionalValueField<?> optionalValueField);

  boolean canSetValue(IOptionalValueField<?> optionalValueField, Object value);
}
