//package declaration
package ch.nolix.system.sqlrawobjectdata.databaseinspector;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.sqlrawobjectdata.sqlapi.ITableDefinition;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class DatabaseInspector {
	
	//static attribute
	private static final TableDefinitionMapper tableDefinitionMapper = new TableDefinitionMapper();
	
	//method
	public IContainer<ITableDefinition> createTableDefinitionsFrom(final ISchemaAdapter schemaAdapter) {
		return schemaAdapter.loadTables().to(tableDefinitionMapper::createTableDefinitionFrom);
	}
}
