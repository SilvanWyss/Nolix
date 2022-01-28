//package declaration
package ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.time.base.Time;
import ch.nolix.systemapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

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
