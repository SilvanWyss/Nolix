//package declaration
package ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//interface
public interface ISchemaReader {
	
	//method declaration
	boolean columnIsEmpty(String tableName, String columnName);
	
	//method declaration
	LinkedList<IColumnDTO> loadColumnsByTableName(String tableName);
	
	//method declaration
	LinkedList<IColumnDTO> loadColumnsByTableId(String tableId);
	
	//method declaration
	IFlatTableDTO loadFlatTableById(String id);
	
	//method declaration
	IFlatTableDTO loadFlatTableByName(String name);
	
	//method declaration
	LinkedList<IFlatTableDTO> loadFlatTables();
	
	//method declaration
	ITableDTO loadTableById(String id);
	
	//method declaration
	ITableDTO loadTableByName(String name);
	
	//method declaration
	LinkedList<ITableDTO> loadTables();
	
	//method declaration
	Time loadSchemaTimestamp();
}
