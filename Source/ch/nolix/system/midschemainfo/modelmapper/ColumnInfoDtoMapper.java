/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.midschemainfo.modelmapper;

import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.modelmapper.IColumnInfoDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class ColumnInfoDtoMapper implements IColumnInfoDtoMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnInfoDto mapMidSchemaColumnDtoToColumnViewDto(
    final ColumnDto columnDto,
    final int oneBasedColumnOrdinalIndex) {
    final var id = columnDto.id();
    final var name = columnDto.name();
    final var fieldType = columnDto.fieldType();
    final var dataType = columnDto.dataType();

    return new ColumnInfoDto(id, name, oneBasedColumnOrdinalIndex, fieldType, dataType);
  }
}
