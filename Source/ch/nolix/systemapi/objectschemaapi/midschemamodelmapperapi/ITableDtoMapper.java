package ch.nolix.systemapi.objectschemaapi.midschemamodelmapperapi;

import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

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
