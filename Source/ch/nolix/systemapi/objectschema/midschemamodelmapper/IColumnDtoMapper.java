package ch.nolix.systemapi.objectschema.midschemamodelmapper;

import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.objectschema.model.IColumn;

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
