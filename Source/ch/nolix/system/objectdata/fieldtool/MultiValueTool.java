package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IMultiValueTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiValue;

public final class MultiValueTool extends FieldTool implements IMultiValueTool {

  @Override
  public boolean canAddGivenValue(final IMultiValue<?> multiValue, final Object value) {
    return //
    canAddValue(multiValue)
    && value != null;
  }

  @Override
  public boolean canClear(final IMultiValue<?> multiValue) {
    return //
    multiValue != null
    && multiValue.belongsToEntity()
    && multiValue.getStoredParentEntity().isOpen();
  }

  @Override
  public <V> boolean canRemoveValue(final IMultiValue<V> multiValue, final V value) {
    return //
    canRemoveValue(multiValue)
    && value != null;
  }

  private boolean canAddValue(final IMultiValue<?> multiValue) {
    return //
    multiValue != null
    && multiValue.belongsToEntity()
    && multiValue.getStoredParentEntity().isOpen();
  }

  private boolean canRemoveValue(final IMultiValue<?> multiValue) {
    return //
    multiValue != null
    && multiValue.isOpen();
  }
}
