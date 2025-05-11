package ch.nolix.systemapi.objectschemaapi.midschemamodelmapperapi;

import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;

/**
 * @author Silvan Wyss
 * @version 2024-12-18
 */
public interface IColumnDtoMapper {

  /**
   * @param column
   * @return a new {@link ColumnDto} from the given column.
   * @throws RuntimeException if the given column is null.
   */
  ColumnDto mapColumnToMidSchemaColumnDto(IColumn column);
}
