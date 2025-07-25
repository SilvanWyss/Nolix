package ch.nolix.systemapi.sqlmidschemaapi.modelmapperapi;

import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.midschemaapi.modelapi.IContentModelDto;

/**
 * @author Silvan Wyss
 * @version 2025-06-20
 */
public interface IContentModelDtoMapper {

  /**
   * @param joinedColumnSqlRecord
   * @return a new {@link IContentModelDto} from the given joinedColumnSqlRecord.
   * @throws RuntimeException if the given joinedColumnSqlRecord is null.
   */
  IContentModelDto mapJoinedColumnSqlRecordToColumnDto(ISqlRecord joinedColumnSqlRecord);
}
