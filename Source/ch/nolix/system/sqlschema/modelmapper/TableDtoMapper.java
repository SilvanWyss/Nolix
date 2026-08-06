/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlschema.modelmapper;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;
import ch.nolix.systemapi.sqlschema.modelmapper.ITableDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class TableDtoMapper implements ITableDtoMapper {
  private static final ColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public TableDto mapSqlRecordsWithNameAndDataTypeToTableDto(
    final String tableName,
    final ExtendedIterable<ISqlRecord> sqlRecordsWithNameAndDataType) {
    final var columns = sqlRecordsWithNameAndDataType.to(COLUMN_DTO_MAPPER::mapSqlRecordWithNameAndDataTypeToColumnDto);

    return new TableDto(tableName, columns);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<TableDto> mapSqlRecordsWithTableNameAndNameAndDataTypeToTableDtos(
    final ExtendedIterable<ISqlRecord> sqlRecordsWithTableNameAndNameAndDataType) {
    final var columnsView = //
    sqlRecordsWithTableNameAndNameAndDataType.getViewOf(
      COLUMN_DTO_MAPPER::mapSqlRecordWithTableNameAndNameAndDataTypeToColumnDto);

    final var columnsGroupedByTable = columnsView.getStoredInGroups(ColumnDto::name);

    return columnsGroupedByTable.to(c -> new TableDto(c.getStoredFirstNonNull().name(), c));
  }
}
