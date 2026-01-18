package ch.nolix.system.objectdata.fieldexaminer;

import ch.nolix.systemapi.objectdata.fieldexaminer.IMultiValueFieldExaminer;
import ch.nolix.systemapi.objectdata.model.IMultiValueField;

/**
 * @author Silvan Wyss
 */
public final class MultiValueFieldTool extends FieldExaminer implements IMultiValueFieldExaminer {
  @Override
  public boolean canAddValue(final IMultiValueField<?> multiValueField) {
    return //
    multiValueField != null
    && multiValueField.isOpen();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canAddValue(final IMultiValueField<?> multiValueField, final Object value) {
    return //
    canAddValue(multiValueField)
    && value != null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canBeCleared(final IMultiValueField<?> multiValueField) {
    return //
    multiValueField != null
    && multiValueField.isOpen();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canRemoveValue(final IMultiValueField<?> multiValueField) {
    return //
    multiValueField != null
    && multiValueField.isOpen();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canRemoveValue(final IMultiValueField<?> multiValueField, final Object value) {
    return //
    canRemoveValue(multiValueField)
    && multiValueField.getAllStoredValues().contains(value);
  }
}
