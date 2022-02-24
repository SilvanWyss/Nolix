//package declaration
package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.system.sqlrawdata.schemainfo.TableInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableDefinitionMapper {
	
	//static attribute 
	private static final ColumnDefinitionMapper columnDefinitionMapper = new ColumnDefinitionMapper();
	
	//method
	public ITableInfo createTableDefinitionFrom(final ITableDTO table) {
		return
		new TableInfo(
			table.getId(),
			table.getName(),
			table.getColumns().to(columnDefinitionMapper::createColumnDefinitionFrom)
		);
	}
}
