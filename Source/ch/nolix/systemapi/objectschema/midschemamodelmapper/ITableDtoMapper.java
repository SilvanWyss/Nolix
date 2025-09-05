package ch.nolix.systemapi.objectschema.midschemamodelmapper;

import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.objectschema.model.ITable;

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
