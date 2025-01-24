package ch.nolix.systemapi.sqlrawschemaapi.rawschemadtomapperapi;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableReferenceDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-25
 */
public interface ITableReferenceDtoMapper {

  /**
   * @param tableReferenceTableSqlRecord
   * @return a new {@link TableReferenceDto} from the given
   *         tableReferenceTableSqlRecord
   * @throws RuntimeException if the given tableReferenceTableSqlRecord is null.
   */
  TableReferenceDto mapTableReferenceTableSqlRecordToTableReferenceDto(ISqlRecord tableReferenceTableSqlRecord);
}
