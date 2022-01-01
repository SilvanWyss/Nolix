//package declaration
package ch.nolix.system.sqlrawobjectschema.tabletable;

import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableTableRecordMapper {
	
	//method
	public TableTableRecord createTableSystemTableRecordFrom(final ITableDTO table) {
		return new TableTableRecord("'" + table.getName() + "'");
	}
}
