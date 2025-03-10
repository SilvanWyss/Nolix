package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IValueTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IValue;

public final class ValueTool extends FieldTool implements IValueTool {

  @Override
  public boolean canSetValue(final IValue<?> value, final Object valueToSet) {
    return //
    canSetValue(value)
    && valueToSet != null;
  }

  private boolean canSetValue(final IValue<?> value) {
    return //
    value != null
    && value.isOpen();
  }
}
