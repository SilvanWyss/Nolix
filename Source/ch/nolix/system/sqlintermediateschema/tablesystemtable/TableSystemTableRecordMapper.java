//package declaration
package ch.nolix.system.sqlintermediateschema.tablesystemtable;

//own imports
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableSystemTableRecordMapper {
	
	//method
	public TableSystemTableRecord createTableSystemTableRecordFrom(final ITableDTO table) {
		return new TableSystemTableRecord("'" + table.getName() + "'");
	}
}
