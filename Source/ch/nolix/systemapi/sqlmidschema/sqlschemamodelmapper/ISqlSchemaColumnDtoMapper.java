/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.sqlschemamodelmapper;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.systemapi.midschema.model.ColumnDto;

/**
 * @author Silvan Wyss
 */
public interface ISqlSchemaColumnDtoMapper {
  /**
   * @param columnDto
   * @return new {@link ch.nolix.systemapi.sqlschema.model.ColumnDto}s from the
   *         given columnDto.
   * @throws RuntimeException if the given columnDto is null.
   */
  IWellOrderContainer<ch.nolix.systemapi.sqlschema.model.ColumnDto> mapColumnDtoToSqlSchemaColumnDtos(ColumnDto columnDto);
}
