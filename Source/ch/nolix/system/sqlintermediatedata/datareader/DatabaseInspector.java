//package declaration
package ch.nolix.system.sqlintermediatedata.datareader;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.system.sqlintermediatedata.sqlapi.ITableDefinition;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//class
final class DatabaseInspector {
	
	//static attribute
	private static final TableDefinitionMapper tableDefinitionMapper = new TableDefinitionMapper();
	
	//method
	public IContainer<ITableDefinition> createTableDefinitionsFrom(final ISchemaAdapter schemaAdapter) {
		return schemaAdapter.loadTables().to(tableDefinitionMapper::createTableDefinitionFrom);
	}
}
