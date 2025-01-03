package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableView;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;

public final class DatabaseInspector {

  private static final TableDefinitionMapper TABLE_DEFINITION_MAPPER = new TableDefinitionMapper();

  public IContainer<ITableView> createTableDefinitionsFrom(final ISchemaReader schemaAdapter) {
    return schemaAdapter.loadTables().to(TABLE_DEFINITION_MAPPER::createTableDefinitionFrom);
  }
}
