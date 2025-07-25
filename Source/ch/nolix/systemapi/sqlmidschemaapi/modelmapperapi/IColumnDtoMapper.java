package ch.nolix.systemapi.sqlmidschemaapi.modelmapperapi;

import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public interface IColumnDtoMapper {

  /**
   * @param joinedColumnSqlRecord
   * @return a new {@link ColumnDto} from the given joinedColumnSqlRecord.
   * @throws RuntimeException if the given joinedColumnSqlRecord is null.
   */
  ColumnDto mapJoinedColumnSqlRecordToColumnDto(ISqlRecord joinedColumnSqlRecord);
}
