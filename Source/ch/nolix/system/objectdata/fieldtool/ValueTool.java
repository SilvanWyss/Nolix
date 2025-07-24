package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IValueTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IValueField;

public final class ValueTool extends FieldTool implements IValueTool {

  @Override
  public boolean canSetValue(final IValueField<?> value, final Object valueToSet) {
    return //
    canSetValue(value)
    && valueToSet != null;
  }

  private boolean canSetValue(final IValueField<?> value) {
    return //
    value != null
    && value.isOpen();
  }
}
