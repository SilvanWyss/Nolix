package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdata.fieldtool.IMultiValueFieldTool;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;

public final class MultiValueFieldTool extends FieldTool implements IMultiValueFieldTool {

  @Override
  public boolean canAddValue(final IMultiValueField<?> multiValueField) {
    return //
    multiValueField != null
    && multiValueField.isOpen();
  }

  @Override
  public boolean canAddValue(final IMultiValueField<?> multiValueField, final Object value) {
    return //
    canAddValue(multiValueField)
    && value != null;
  }

  @Override
  public boolean canClear(final IMultiValueField<?> multiValueField) {
    return //
    multiValueField != null
    && multiValueField.isOpen();
  }

  @Override
  public boolean canRemoveValue(final IMultiValueField<?> multiValueField) {
    return //
    multiValueField != null
    && multiValueField.isOpen();
  }

  @Override
  public boolean canRemoveValue(final IMultiValueField<?> multiValueField, final Object value) {
    return //
    canRemoveValue(multiValueField)
    && multiValueField.getAllStoredValues().contains(value);
  }
}
