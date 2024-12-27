package ch.nolix.system.objectschema.rawschemadtomapper;

import ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi.ITableDtoMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.dto.TableDto;

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
    final var columnDtos = columns.to(COLUMN_DTO_MAPPER::mapColumnToColumnDto);

    return new TableDto(id, name, columnDtos);
  }
}
