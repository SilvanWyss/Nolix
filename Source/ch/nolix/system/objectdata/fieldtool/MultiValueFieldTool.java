package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdata.fieldtool.IMultiValueFieldTool;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;

public final class MultiValueFieldTool extends FieldTool implements IMultiValueFieldTool {

  @Override
  public boolean canAddValue(final IMultiValueField<?> multiValueField, final Object value) {
    return //
    canAddValue(multiValueField)
    && value != null;
  }

  @Override
  public boolean canClear(final IMultiValueField<?> multiValue) {
    return //
    multiValue != null
    && multiValue.isOpen();
  }

  @Override
  public boolean canRemoveValue(final IMultiValueField<?> multiValueField, final Object value) {
    return //
    canRemoveValue(multiValueField)
    && multiValueField.getAllStoredValues().contains(value);
  }

  private boolean canAddValue(final IMultiValueField<?> multiValue) {
    return //
    multiValue != null
    && multiValue.isOpen();
  }

  private boolean canRemoveValue(final IMultiValueField<?> multiValue) {
    return //
    multiValue != null
    && multiValue.isOpen();
  }
}
