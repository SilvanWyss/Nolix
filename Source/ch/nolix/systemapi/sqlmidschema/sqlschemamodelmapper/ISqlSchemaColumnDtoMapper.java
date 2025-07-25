package ch.nolix.systemapi.sqlmidschema.sqlschemamodelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.model.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-01
 */
public interface ISqlSchemaColumnDtoMapper {

  /**
   * @param columnDto
   * @return new {@link ch.nolix.systemapi.sqlschema.model.ColumnDto}s from the
   *         given columnDto.
   * @throws RuntimeException if the given columnDto is null.
   */
  IContainer<ch.nolix.systemapi.sqlschema.model.ColumnDto> mapColumnDtoToSqlSchemaColumnDtos(ColumnDto columnDto);
}
