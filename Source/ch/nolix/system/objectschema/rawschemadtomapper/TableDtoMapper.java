package ch.nolix.system.objectschema.rawschemadtomapper;

import ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi.ITableDtoMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.TableDto;

public final class TableDtoMapper implements ITableDtoMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public TableDto mapTableToTableDto(final ITable table) {

    final var id = table.getId();
    final var name = table.getName();
    final var columns = table.getStoredColumns();
    final var columnDtos = columns.to(IColumn::toDto);

    return new TableDto(id, name, columnDtos);
  }
}
