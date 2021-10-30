//package declaration
package ch.nolix.system.sqlrawobjectschema.tablesystemtable;

import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableSystemTableRecordMapper {
	
	//method
	public TableSystemTableRecord createTableSystemTableRecordFrom(final ITableDTO table) {
		return new TableSystemTableRecord("'" + table.getName() + "'");
	}
}
