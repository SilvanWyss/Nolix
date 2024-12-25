package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IMultiValueTool;
import ch.nolix.systemapi.rawdataapi.datadto.StringContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityUpdateDto;

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

  @Override
  public <V> EntityUpdateDto createEntityUpdateDtoForAddedValue(
    final IMultiValue<V> multiValue,
    final V addedValue) {

    final var parentEntity = multiValue.getStoredParentEntity();

    return //
    new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      StringContentFieldDto.withColumnNameAndContent(multiValue.getName(), ""));
  }

  @Override
  public EntityUpdateDto createEntityUpdateDtoForClear(final IMultiValue<?> multiValue) {

    final var parentEntity = multiValue.getStoredParentEntity();

    return //
    new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      StringContentFieldDto.withColumnName(multiValue.getName()));
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
