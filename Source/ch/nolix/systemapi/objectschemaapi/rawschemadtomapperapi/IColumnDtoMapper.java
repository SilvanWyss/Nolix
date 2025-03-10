package ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-18
 */
public interface IColumnDtoMapper {

  /**
   * @param column
   * @return new {@link ColumnDto}s from the given column.
   * @throws RuntimeException if the given column is null.
   */
  IContainer<ColumnDto> mapColumnToColumnDtos(IColumn column);
}
