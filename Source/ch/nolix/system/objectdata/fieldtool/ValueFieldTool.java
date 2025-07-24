package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IValueFieldTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IValueField;

public final class ValueFieldTool extends FieldTool implements IValueFieldTool {

  @Override
  public boolean canSetValue(final IValueField<?> valueField) {
    return //
    valueField != null
    && valueField.isOpen();
  }

  @Override
  public boolean canSetValue(final IValueField<?> valueField, final Object value) {
    return //
    canSetValue(valueField)
    && value != null;
  }
}
