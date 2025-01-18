package ch.nolix.systemapi.sqlschemaapi.flatmodelmapperapi;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.sqlschemaapi.flatmodelapi.FlatTableDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-18
 */
public interface IFlatTableDtoMapper {

  /**
   * @param sqlRecord
   * @return a new {@link FlatTableDto} from the given sqlRecord.
   * @throws RuntimeException if the given sqlRecord is null.
   */
  FlatTableDto mapSqlRecordToFlatTableDto(ISqlRecord sqlRecord);
}
