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
	boolean columnIsEmpty(String tableName, String columnHeader);
	
	//method declaration
	LinkedList<IColumnDTO> loadColumns(String tableName);
	
	//method declaration
	LinkedList<IFlatTableDTO> loadFlatTables();
	
	//method declaration
	ITableDTO loadTable(String tableName);
	
	//method declaration
	LinkedList<ITableDTO> loadTables();
	
	//method declaration
	Time loadSchemaTimestamp();
}
