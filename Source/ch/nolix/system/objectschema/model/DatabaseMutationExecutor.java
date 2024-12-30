package ch.nolix.system.objectschema.model;

import ch.nolix.system.objectschema.rawschemadtomapper.TableDtoMapper;
import ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi.ITableDtoMapper;

final class DatabaseMutationExecutor {

  private static final ITableDtoMapper TABLE_DTO_MAPPER = new TableDtoMapper();

  public void addTableToDatabase(
    final Database database,
    final Table table) {

    database.addTableAttribute(table);
    table.setParentDatabase(database);

    if (database.isConnectedWithRealDatabase()) {

      final var tableDto = TABLE_DTO_MAPPER.mapTableToTableDto(table);

      database.internalGetStoredRawSchemaAdapter().addTable(tableDto);
    }

    database.internalSetEdited();
  }
}
