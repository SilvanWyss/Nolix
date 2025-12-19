package ch.nolix.systemapi.sqlschema.modelmapper;

import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;

/**
 * @author Silvan Wyss
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
