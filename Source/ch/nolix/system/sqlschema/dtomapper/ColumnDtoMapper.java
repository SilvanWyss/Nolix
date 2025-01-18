package ch.nolix.system.sqlschema.dtomapper;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.sqlschemaapi.dtomapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.DataTypeDto;

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

    return ColumnDto.withNameAndDataType(name, dataType);
  }
}
