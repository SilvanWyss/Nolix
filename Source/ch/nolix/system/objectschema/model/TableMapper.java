/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.model;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public final class TableMapper {
  private TableMapper() {
  }

  public static IContainer<Table> mapMidSchemaTableDtosToLoadedTables(final IContainer<TableDto> midSchemaTableDtos) {
    final var tables = mapMidSchemaTableDtosToEmptyLoadedTables(midSchemaTableDtos);

    for (final var t : tables) {
      final var id = t.getId();
      final var midschemaTableDto = midSchemaTableDtos.getStoredFirst(m -> m.id().equals(id));
      final var columns = ColumnMapper.mapMidSchemaTableDtoToLoadedColumns(midschemaTableDto, tables);

      columns.forEach(t::addColumnAttribute);
    }

    return tables;
  }

  private static IContainer<Table> mapMidSchemaTableDtosToEmptyLoadedTables(
    final IContainer<TableDto> midSchemaTableDtos) {
    return midSchemaTableDtos.to(TableMapper::mapMidSchemaTableDtoToEmptyTable);
  }

  private static Table mapMidSchemaTableDtoToEmptyTable(final TableDto midSchemaTableDto) {
    final var id = midSchemaTableDto.id();
    final var name = midSchemaTableDto.name();
    final var table = Table.withIdAndName(id, name);

    table.setLoaded();

    return table;
  }
}
