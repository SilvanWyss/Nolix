package ch.nolix.systemapi.sqlschemaapi.modelmapperapi;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public interface IColumnDtoMapper {

  /**
   * @param sqlRecordWithNameAndDataType
   * @return a new {@link ColumnDto} from the given sqlRecordWithNameAndDataType.
   * @throws RuntimeException if the given sqlRecordWithNameAndDataType is null.
   */
  ColumnDto mapSqlRecordWithNameAndDataTypeToColumnDto(ISqlRecord sqlRecordWithNameAndDataType);

  /**
   * @param sqlRecordWithTableNameAndNameAndDataType
   * @return a new {@link ColumnDto} from the given
   *         sqlRecordWithTableNameAndNameAndDataType.
   * @throws RuntimeException if the given
   *                          sqlRecordWithTableNameAndNameAndDataType is null.
   */
  ColumnDto mapSqlRecordWithTableNameAndNameAndDataTypeToColumnDto(ISqlRecord sqlRecordWithTableNameAndNameAndDataType);
}
