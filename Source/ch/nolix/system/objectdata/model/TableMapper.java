package ch.nolix.system.objectdata.model;

import ch.nolix.system.objectdata.schemasearcher.SchemaSearcher;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.schemamodelsearcherapi.ISchemaSearcher;

public final class TableMapper {

  private static final ISchemaSearcher SCHEMA_SEARCHER = new SchemaSearcher();

  @SuppressWarnings("unchecked")
  public Table<IEntity> mapTableDtoToTableWithoutColumnsAndWithoutEntities(
    final TableDto rawTableDto,
    final Database database) {

    final var tableName = rawTableDto.name();
    final var tableId = rawTableDto.id();
    final var entityTypeSet = database.getEntityTypeSet();
    final var entityType = (Class<IEntity>) (SCHEMA_SEARCHER.getEntityTypeByName(entityTypeSet, tableName));

    return Table.withParentDatabaseAndNameAndIdAndEntityType(database, tableName, tableId, entityType);
  }
}
