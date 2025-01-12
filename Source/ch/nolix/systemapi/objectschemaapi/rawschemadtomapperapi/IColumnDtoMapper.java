package ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi;

import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-18
 */
public interface IColumnDtoMapper {

  /**
   * @param column
   * @return a new {@link TableDto} from the given column.
   * @throws RuntimeException if the given column is null.
   */
  ColumnDto mapColumnToColumnDto(IColumn column);
}
