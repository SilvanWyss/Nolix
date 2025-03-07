package ch.nolix.systemapi.sqlschemaapi.modelmapperapi;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public interface IColumnDtoMapper {

  /**
   * @param sqlRecord
   * @return a new {@link ColumnDto} from the given sqlRecord.
   * @throws RuntimeException if the given sqlRecord is null.
   */
  ColumnDto mapSqlRecordToColumnDto(ISqlRecord sqlRecord);
}
