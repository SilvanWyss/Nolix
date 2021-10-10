//package declaration
package ch.nolix.system.sqlintermediatedata.datareader;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.intermediatedataapi.recorddtoapi.ITableDefinitionDTO;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
final class DatabaseInspector {
	
	//static attribute
	private static final TableDefinitionDTOMapper tableDefinitionDTOMapper = new TableDefinitionDTOMapper();
	
	//method
	public IContainer<ITableDefinitionDTO> createTableDefinitionsFrom(final ISchemaAdapter schemaAdapter) {
		return schemaAdapter.loadTables().to(tableDefinitionDTOMapper::createTableDefinitionDTOFrom);
	}
}
