package ch.nolix.system.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.fieldexaminer.IMultiValueFieldExaminer;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;

public final class MultiValueFieldTool extends FieldExaminer implements IMultiValueFieldExaminer {
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
  public boolean canBeCleared(final IMultiValueField<?> multiValueField) {
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
