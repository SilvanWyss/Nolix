//package declaration
package ch.nolix.system.sqldatabaserawdata.databaseinspector;

import ch.nolix.system.sqldatabaserawdata.schemainfo.TableInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;
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
