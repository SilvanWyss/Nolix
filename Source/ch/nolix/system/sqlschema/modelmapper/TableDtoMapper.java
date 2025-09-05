package ch.nolix.system.sqlschema.modelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;
import ch.nolix.systemapi.sqlschema.modelmapper.IColumnDtoMapper;
import ch.nolix.systemapi.sqlschema.modelmapper.ITableDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2025-05-11
 */
public final class TableDtoMapper implements ITableDtoMapper {
  private static final IColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public TableDto mapSqlRecordsWithNameAndDataTypeToTableDto(
    final String tableName,
    final IContainer<ISqlRecord> sqlRecordsWithNameAndDataType) {
    final var columns = sqlRecordsWithNameAndDataType.to(COLUMN_DTO_MAPPER::mapSqlRecordWithNameAndDataTypeToColumnDto);

    return new TableDto(tableName, columns);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<TableDto> mapSqlRecordsWithTableNameAndNameAndDataTypeToTableDtos(
    final IContainer<ISqlRecord> sqlRecordsWithTableNameAndNameAndDataType) {
    final var columns = //
    sqlRecordsWithTableNameAndNameAndDataType.to(
      COLUMN_DTO_MAPPER::mapSqlRecordWithTableNameAndNameAndDataTypeToColumnDto);

    final var columnsGroupedByTable = columns.getStoredInGroups(ColumnDto::name);

    return columnsGroupedByTable.to(c -> new TableDto(c.getStoredFirst().name(), c));
  }
}
