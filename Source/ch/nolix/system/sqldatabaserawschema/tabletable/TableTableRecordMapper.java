//package declaration
package ch.nolix.system.sqldatabaserawschema.tabletable;

//own imports
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
public final class TableTableRecordMapper {
	
	//method
	public TableTableRecord createTableSystemTableRecordFrom(final ITableDTO table) {
		return
		new TableTableRecord(
			"'" + table.getId() + "'",
			"'" + table.getName() + "'"
		);
	}
}
