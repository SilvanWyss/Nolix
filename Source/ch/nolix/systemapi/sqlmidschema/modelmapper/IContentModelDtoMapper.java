package ch.nolix.systemapi.sqlmidschema.modelmapper;

import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.midschema.model.ContentModelDto;

/**
 * @author Silvan Wyss
 * @version 2025-06-20
 */
public interface IContentModelDtoMapper {
  /**
   * @param joinedColumnSqlRecord
   * @return a new {@link ContentModelDto} from the given joinedColumnSqlRecord.
   * @throws RuntimeException if the given joinedColumnSqlRecord is null.
   */
  ContentModelDto mapJoinedColumnSqlRecordToColumnDto(ISqlRecord joinedColumnSqlRecord);
}
