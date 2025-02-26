package ch.nolix.systemapi.sqlrawschemaapi.rawschemadtomapperapi;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public interface IColumnDtoMapper {

  /**
   * @param columnTableSqlRecord
   * @return a new {@link ColumnDto} from the given columnTableSqlRecord
   * @throws RuntimeException if the given columnTableSqlRecord is null.
   */
  ColumnDto mapColumnTableSqlRecordToColumnDto(ISqlRecord columnTableSqlRecord);
}
