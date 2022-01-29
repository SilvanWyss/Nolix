//package declaration
package ch.nolix.system.sqlrawobjectdata.databaseinspector;

import ch.nolix.system.sqlrawobjectdata.schemainfo.TableDefinition;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableDefinitionMapper {
	
	//static attribute 
	private static final ColumnDefinitionMapper columnDefinitionMapper = new ColumnDefinitionMapper();
	
	//method
	public ITableInfo createTableDefinitionFrom(final ITableDTO table) {
		return
		new TableDefinition(
			table.getId(),
			table.getName(),
			table.getColumns().to(columnDefinitionMapper::createColumnDefinitionFrom)
		);
	}
}
