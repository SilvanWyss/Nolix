/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.midschemamodelmapper;

import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.objectschema.model.IColumn;

/**
 * @author Silvan Wyss
 */
public interface IColumnDtoMapper {
  /**
   * @param column
   * @return a new {@link ColumnDto} from the given column.
   * @throws RuntimeException if the given column is null.
   */
  ColumnDto mapColumnToMidSchemaColumnDto(IColumn column);
}
