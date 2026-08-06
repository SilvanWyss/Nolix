/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.system.objectdata.schemasearcher.SchemaSearcher;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 */
public final class TableMapper {
  private static final SchemaSearcher SCHEMA_SEARCHER = new SchemaSearcher();

  private TableMapper() {
  }

  @SuppressWarnings("unchecked")
  public static Table<IEntity> mapMidSchemaTableDtoToTableWithoutColumns(
    final TableDto midTableDto,
    final Database database) {
    final var tableName = midTableDto.name();
    final var tableId = midTableDto.id();
    final var entityTypeSet = database.getEntityTypeSet();
    final var entityType = (Class<IEntity>) (SCHEMA_SEARCHER.getEntityTypeByName(entityTypeSet, tableName));

    return Table.withParentDatabaseAndNameAndIdAndEntityType(database, tableName, tableId, entityType);
  }
}
