package ch.nolix.systemapi.sqlrawschemaapi.rawschemadtomapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawschemaapi.dto.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public interface IColumnDtoMapper {

  /**
   * @param columnTableSqlRecord
   * @return a new {@link ColumnDto} from the given columnTableSqlRecord
   */
  ColumnDto mapColumnTableSqlRecordToColumnDto(IContainer<String> columnTableSqlRecord);
}
