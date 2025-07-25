package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdata.fieldtool.IOptionalValueFieldTool;
import ch.nolix.systemapi.objectdata.model.IOptionalValueField;

public final class OptionalValueFieldTool extends FieldTool implements IOptionalValueFieldTool {

  @Override
  public boolean canSetValue(final IOptionalValueField<?> optionalValueField) {
    return //
    optionalValueField != null
    && optionalValueField.isOpen();
  }

  @Override
  public boolean canSetValue(final IOptionalValueField<?> optionalValueField, final Object value) {
    return //
    canSetValue(optionalValueField)
    && value != null;
  }
}
