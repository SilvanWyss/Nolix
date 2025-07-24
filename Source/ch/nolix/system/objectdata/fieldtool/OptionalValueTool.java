package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IOptionalValueTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IOptionalValueField;

public final class OptionalValueTool extends FieldTool implements IOptionalValueTool {

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
