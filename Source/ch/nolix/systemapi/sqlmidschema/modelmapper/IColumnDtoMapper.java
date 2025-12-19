package ch.nolix.systemapi.sqlmidschema.modelmapper;

import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.midschema.model.ColumnDto;

/**
 * @author Silvan Wyss
 */
public interface IColumnDtoMapper {
  /**
   * @param joinedColumnSqlRecord
   * @return a new {@link ColumnDto} from the given joinedColumnSqlRecord.
   * @throws RuntimeException if the given joinedColumnSqlRecord is null.
   */
  ColumnDto mapJoinedColumnSqlRecordToColumnDto(ISqlRecord joinedColumnSqlRecord);
}
