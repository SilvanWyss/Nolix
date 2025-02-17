package ch.nolix.system.objectdata.model;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.schemasearcher.SchemaSearcher;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemamodelsearcherapi.ISchemaSearcher;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;

final class TableMapper {

  private static final ISchemaSearcher SCHEMA_SEARCHER = new SchemaSearcher();

  private static final ColumnMapper COLUMN_MAPPER = new ColumnMapper();

  @SuppressWarnings("unchecked")
  public Table<IEntity> createEmptyTableFromTableDtoForDatabase(
    final TableDto tableDto,
    final Database database) {
    return Table.withParentDatabaseAndNameAndIdAndEntityClassAndColumns(
      database,
      tableDto.name(),
      tableDto.id(),
      (Class<IEntity>) (SCHEMA_SEARCHER.getEntityTypeByName(database.internalGetSchema(), tableDto.name())));
  }

  public ITable<IEntity> createTableFromTableDtoForDatabaseUsingGivenReferencableTables(
    final TableDto tableDto,
    final Database database,
    final IContainer<ITable<IEntity>> referencableTables) {

    final var table = createEmptyTableFromTableDtoForDatabase(tableDto, database);

    final var columns = tableDto.columns()
      .to(
        c -> COLUMN_MAPPER.mapRawSchemaColumnDtoToColumnView(c, table,
          referencableTables));

    table.internalSetColumns(columns);

    return table;
  }
}
