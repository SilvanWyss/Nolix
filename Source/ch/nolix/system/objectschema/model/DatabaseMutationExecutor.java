package ch.nolix.system.objectschema.model;

import ch.nolix.system.objectschema.midschemamodelmapper.TableDtoMapper;
import ch.nolix.systemapi.objectschemaapi.midschemamodelmapperapi.ITableDtoMapper;

final class DatabaseMutationExecutor {

  private static final ITableDtoMapper TABLE_DTO_MAPPER = new TableDtoMapper();

  public void addTableToDatabase(
    final Database database,
    final Table table) {

    database.addTableAttribute(table);
    table.setParentDatabase(database);

    if (database.isConnectedWithRealDatabase()) {

      final var tableDto = TABLE_DTO_MAPPER.mapTableToTableDto(table);

      database.getStoredMidSchemaAdapter().addTable(tableDto);
    }

    database.internalSetEdited();
  }
}
