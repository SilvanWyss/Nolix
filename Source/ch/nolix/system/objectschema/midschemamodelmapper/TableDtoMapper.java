/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.midschemamodelmapper;

import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.objectschema.midschemamodelmapper.IColumnDtoMapper;
import ch.nolix.systemapi.objectschema.midschemamodelmapper.ITableDtoMapper;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public final class TableDtoMapper implements ITableDtoMapper {
  private static final IColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public TableDto mapTableToTableDto(final ITable table) {
    final var id = table.getId();
    final var name = table.getName();
    final var columns = table.getStoredColumns();
    final var columnDtos = columns.to(COLUMN_DTO_MAPPER::mapColumnToMidSchemaColumnDto);

    return new TableDto(id, name, columnDtos);
  }
}
