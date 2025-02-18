package ch.nolix.system.sqlschema.modelmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.DataTypeDto;
import ch.nolix.systemapi.sqlschemaapi.modelmapperapi.IColumnDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public final class ColumnDtoMapper implements IColumnDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnDto mapSqlRecordToColumnDto(final ISqlRecord sqlRecord) {

    final var name = sqlRecord.getStoredAt1BasedIndex(1);
    final var dataType = DataTypeDto.withName(sqlRecord.getStoredAt1BasedIndex(2));

    return new ColumnDto(name, dataType, ImmutableList.createEmpty());
  }
}
