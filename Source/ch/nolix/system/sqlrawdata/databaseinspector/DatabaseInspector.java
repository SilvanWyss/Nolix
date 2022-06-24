//package declaration
package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class DatabaseInspector {
	
	//static attribute
	private static final TableDefinitionMapper tableDefinitionMapper = new TableDefinitionMapper();
	
	//method
	public IContainer<ITableInfo> createTableDefinitionsFrom(final ISchemaAdapter schemaAdapter) {
		return schemaAdapter.loadTables().to(tableDefinitionMapper::createTableDefinitionFrom);
	}
}
