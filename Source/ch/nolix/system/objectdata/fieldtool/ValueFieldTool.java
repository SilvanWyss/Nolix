package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IValueFieldTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IValueField;

public final class ValueFieldTool extends FieldTool implements IValueFieldTool {

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
