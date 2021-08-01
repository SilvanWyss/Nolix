//package declaration
package ch.nolix.techapi.databaseschemaapi.intermediateschemaapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.databaseschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IColumnDTO;

//interface
public interface IIntermediateSchemaReader {
	
	//method declaration
	boolean columnIsEmpty(String tableName, String columnHeader);
	
	//method declaration
	LinkedList<IColumnDTO> loadColumnsOfTable(String tableName);
	
	//method declaration
	LinkedList<IFlatTableDTO> loadFlatTables();
	
	//method declaration
	Time loadSchemaTimestamp();
}
