/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlschema.modelmapper;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;
import ch.nolix.systemapi.sqlschema.modelmapper.IColumnDtoMapper;
import ch.nolix.systemapi.sqlschema.modelmapper.ITableDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class TableDtoMapper implements ITableDtoMapper {
  private static final IColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public TableDto mapSqlRecordsWithNameAndDataTypeToTableDto(
    final String tableName,
    final IWellOrderContainer<ISqlRecord> sqlRecordsWithNameAndDataType) {
    final var columns = sqlRecordsWithNameAndDataType.to(COLUMN_DTO_MAPPER::mapSqlRecordWithNameAndDataTypeToColumnDto);

    return new TableDto(tableName, columns);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IWellOrderContainer<TableDto> mapSqlRecordsWithTableNameAndNameAndDataTypeToTableDtos(
    final IWellOrderContainer<ISqlRecord> sqlRecordsWithTableNameAndNameAndDataType) {
    final var columnsView = //
    sqlRecordsWithTableNameAndNameAndDataType.getViewOf(
      COLUMN_DTO_MAPPER::mapSqlRecordWithTableNameAndNameAndDataTypeToColumnDto);

    final var columnsGroupedByTable = columnsView.getStoredInGroups(ColumnDto::name);

    return columnsGroupedByTable.to(c -> new TableDto(c.getStoredFirst().name(), c));
  }
}
