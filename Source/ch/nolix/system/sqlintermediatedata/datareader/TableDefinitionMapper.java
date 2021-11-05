//package declaration
package ch.nolix.system.sqlintermediatedata.datareader;

//own imports
import ch.nolix.system.sqlintermediatedata.schema.TableDefinition;
import ch.nolix.system.sqlintermediatedata.sqlapi.ITableDefinition;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableDefinitionMapper {
	
	//static attribute 
	private static final ColumnDefinitionMapper columnDefinitionMapper = new ColumnDefinitionMapper();
	
	//method
	public ITableDefinition createTableDefinitionFrom(final ITableDTO table) {
		return
		new TableDefinition(
			table.getName(),
			table.getColumns().to(columnDefinitionMapper::createColumnDefinitionFrom)
		);
	}
}
