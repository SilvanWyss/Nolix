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
  public ColumnDto mapSqlRecordWithNameAndDataTypeToColumnDto(final ISqlRecord sqlRecordWithNameAndDataType) {

    final var name = sqlRecordWithNameAndDataType.getStoredAt1BasedIndex(1);
    final var dataType = new DataTypeDto(sqlRecordWithNameAndDataType.getStoredAt1BasedIndex(2), null);

    return new ColumnDto(name, dataType, ImmutableList.createEmpty());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnDto mapSqlRecordWithTableNameAndNameAndDataTypeToColumnDto(
    final ISqlRecord sqlRecordWithTableNameAndNameAndDataType) {

    final var name = sqlRecordWithTableNameAndNameAndDataType.getStoredAt1BasedIndex(2);
    final var dataType = new DataTypeDto(sqlRecordWithTableNameAndNameAndDataType.getStoredAt1BasedIndex(3), null);

    return new ColumnDto(name, dataType, ImmutableList.createEmpty());
  }

}
