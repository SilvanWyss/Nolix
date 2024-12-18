package ch.nolix.system.objectdata.fieldtool;

import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IValueTool;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldWithContentAsStringDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityUpdateDto;

public final class ValueTool extends FieldTool implements IValueTool {

  @Override
  public boolean canSetValue(final IValue<?> value, final Object valueToSet) {
    return //
    canSetValue(value)
    && valueToSet != null;
  }

  @Override
  public EntityUpdateDto createEntityUpdateDtoForSetValue(final IValue<?> value, final Object setValue) {

    final var parentEntity = value.getStoredParentEntity();

    return //
    new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      ContentFieldWithContentAsStringDto.withColumnNameAndContent(value.getName(), setValue.toString()));
  }

  private boolean canSetValue(final IValue<?> value) {
    return //
    value != null
    && value.isOpen();
  }
}
