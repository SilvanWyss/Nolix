//package declaration
package ch.nolix.system.sqlrawobjectdata.datareader;

import ch.nolix.system.sqlrawobjectdata.schema.TableDefinition;
import ch.nolix.system.sqlrawobjectdata.sqlapi.ITableDefinition;
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
