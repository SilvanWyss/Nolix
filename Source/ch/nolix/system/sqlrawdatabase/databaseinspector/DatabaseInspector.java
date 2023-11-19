//package declaration
package ch.nolix.system.sqlrawdatabase.databaseinspector;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class DatabaseInspector {

  //constant
  private static final TableDefinitionMapper TABLE_DEFINITION_MAPPER = new TableDefinitionMapper();

  //method
  public IContainer<ITableInfo> createTableDefinitionsFrom(final ISchemaAdapter schemaAdapter) {
    return schemaAdapter.loadTables().to(TABLE_DEFINITION_MAPPER::createTableDefinitionFrom);
  }
}
