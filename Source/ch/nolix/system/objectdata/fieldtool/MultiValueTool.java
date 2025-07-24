package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IMultiValueTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValueField;

public final class MultiValueTool extends FieldTool implements IMultiValueTool {

  @Override
  public boolean canAddGivenValue(final IMultiValueField<?> multiValue, final Object value) {
    return //
    canAddValue(multiValue)
    && value != null;
  }

  @Override
  public boolean canClear(final IMultiValueField<?> multiValue) {
    return //
    multiValue != null
    && multiValue.belongsToEntity()
    && multiValue.getStoredParentEntity().isOpen();
  }

  @Override
  public <V> boolean canRemoveValue(final IMultiValueField<V> multiValue, final V value) {
    return //
    canRemoveValue(multiValue)
    && value != null;
  }

  private boolean canAddValue(final IMultiValueField<?> multiValue) {
    return //
    multiValue != null
    && multiValue.belongsToEntity()
    && multiValue.getStoredParentEntity().isOpen();
  }

  private boolean canRemoveValue(final IMultiValueField<?> multiValue) {
    return //
    multiValue != null
    && multiValue.isOpen();
  }
}
