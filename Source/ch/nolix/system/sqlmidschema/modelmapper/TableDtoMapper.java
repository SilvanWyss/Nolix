/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.modelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.sqlmidschema.modelmapper.IColumnDtoMapper;
import ch.nolix.systemapi.sqlmidschema.modelmapper.ITableDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class TableDtoMapper implements ITableDtoMapper {
  private static final IColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public TableDto mapJoinedColumnSqlRecordsToTableDto(final IContainer<ISqlRecord> joinedColumnSqlRecords) {
    final var firstJoinedColumnSqlRecord = joinedColumnSqlRecords.getStoredFirst();
    final var id = firstJoinedColumnSqlRecord.getStoredAtOneBasedIndex(3);
    final var name = firstJoinedColumnSqlRecord.getStoredAtOneBasedIndex(4);
    final var columns = joinedColumnSqlRecords.to(COLUMN_DTO_MAPPER::mapJoinedColumnSqlRecordToColumnDto);

    return new TableDto(id, name, columns);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<TableDto> mapJoinedColumnSqlRecordsToTableDtos(
    final IContainer<ISqlRecord> joinedColumnSqlRecords) {
    final var joinedColumnSqlRecordsGroupedByTable = //
    joinedColumnSqlRecords.getStoredInGroups(r -> r.getStoredAtOneBasedIndex(3));

    return joinedColumnSqlRecordsGroupedByTable.to(this::mapJoinedColumnSqlRecordsToTableDto);
  }
}
