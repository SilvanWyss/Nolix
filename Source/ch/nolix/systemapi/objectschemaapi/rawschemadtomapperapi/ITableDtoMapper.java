package ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi;

import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.dto.TableDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-18
 */
public interface ITableDtoMapper {

  /**
   * @param table
   * @return a new {@link TableDto} from the given table.
   * @throws RuntimeException if the given table is null.
   */
  TableDto mapTableToTableDto(ITable table);
}
