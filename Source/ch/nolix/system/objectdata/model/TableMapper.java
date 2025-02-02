package ch.nolix.system.objectdata.model;

import ch.nolix.system.objectdata.schemasearcher.SchemaSearcher;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.schemamodelsearcherapi.ISchemaSearcher;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;

public final class TableMapper {

  private static final ISchemaSearcher SCHEMA_SEARCHER = new SchemaSearcher();

  @SuppressWarnings("unchecked")
  public Table<IEntity> mapTableDtoToTableWithoutColumnsAndWithoutEntities(
    final TableDto rawTableDto,
    final Database database) {

    final var tableName = rawTableDto.name();
    final var tableId = rawTableDto.id();

    final var entityType = //
    (Class<IEntity>) (SCHEMA_SEARCHER.getEntityTypeByName(database.internalGetSchema(), tableName));

    return Table.withParentDatabaseAndNameAndIdAndEntityType(database, tableName, tableId, entityType);
  }
}
