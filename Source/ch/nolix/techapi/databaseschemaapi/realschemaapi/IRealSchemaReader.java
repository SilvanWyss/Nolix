//package declaration
package ch.nolix.techapi.databaseschemaapi.realschemaapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.databaseschema.flatschemadto.FlatTableDTO;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IColumnDTO;

//interface
public interface IRealSchemaReader {
	
	//method declaration
	boolean columnIsEmpty(String tableName, String columnHeader);
	
	//method declaration
	LinkedList<IColumnDTO> loadColumnsOfTable(String tableName);
	
	//method declaration
	LinkedList<FlatTableDTO> loadFlatTables();
	
	//method declaration
	Time loadSchemaTimestamp();
}
