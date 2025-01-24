package ch.nolix.systemapi.sqlrawschemaapi.rawschemadtomapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableReferenceDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public interface IColumnDtoMapper {

  /**
   * @param columnTableSqlRecord
   * @param tableReferences
   * @return a new {@link ColumnDto} from the given columnTableSqlRecord
   * @throws RuntimeException if the given columnTableSqlRecord is null.
   * @throws RuntimeException if the given tableReferences is null.
   * @throws RuntimeException if one of the given columnTableSqlRecord is null.
   */
  ColumnDto mapColumnTableSqlRecordToColumnDto(
    ISqlRecord columnTableSqlRecord,
    IContainer<TableReferenceDto> tableReferences);
}
