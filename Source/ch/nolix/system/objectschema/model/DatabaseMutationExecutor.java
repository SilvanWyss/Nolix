/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.model;

import ch.nolix.system.objectschema.midschemamodelmapper.TableDtoMapper;

final class DatabaseMutationExecutor {
  private static final TableDtoMapper TABLE_DTO_MAPPER = new TableDtoMapper();

  private DatabaseMutationExecutor() {
  }

  public static void addTableToDatabase(final Database database, final Table table) {
    database.addTableAttribute(table);
    table.setParentDatabase(database);

    if (database.isConnectedWithRealDatabase()) {
      final var tableDto = TABLE_DTO_MAPPER.mapTableToTableDto(table);

      database.getStoredMidSchemaAdapter().addTable(tableDto);
    }

    database.setEdited();
  }
}
