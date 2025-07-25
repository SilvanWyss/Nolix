package ch.nolix.systemapi.sqlmidschemaapi.sqlschemamodelmapperapi;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-01
 */
public interface ISqlSchemaColumnDtoMapper {

  /**
   * @param columnDto
   * @return new {@link ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto}s from
   *         the given columnDto.
   * @throws RuntimeException if the given columnDto is null.
   */
  IContainer<ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto> mapColumnDtoToSqlSchemaColumnDtos(ColumnDto columnDto);
}
