package ch.nolix.system.objectschema.midschemamodelmapper;

import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.objectschemaapi.midschemamodelmapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.objectschemaapi.midschemamodelmapperapi.ITableDtoMapper;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

/**
 * @author Silvan Wyss
 * @version 2024-12-18
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
