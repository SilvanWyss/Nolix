//package declaration
package ch.nolix.system.sqlrawobjectdata.databaseinspector;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class DatabaseInspector {
	
	//static attribute
	private static final TableDefinitionMapper tableDefinitionMapper = new TableDefinitionMapper();
	
	//method
	public IContainer<ITableInfo> createTableDefinitionsFrom(final ISchemaAdapter schemaAdapter) {
		return schemaAdapter.loadTables().to(tableDefinitionMapper::createTableDefinitionFrom);
	}
}
