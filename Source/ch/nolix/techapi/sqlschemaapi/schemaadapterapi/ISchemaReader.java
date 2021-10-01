//package declaration
package ch.nolix.techapi.sqlschemaapi.schemaadapterapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.techapi.sqlschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO;

//interface
public interface ISchemaReader {
	
	//method declaration
	boolean columnsIsEmpty(String tableName, String columnName);
	
	//method declaration
	LinkedList<IColumnDTO> loadColumns(String tableName);
	
	//method declaration
	LinkedList<IFlatTableDTO> loadFlatTables();
	
	//method declaration
	LinkedList<ITableDTO> loadTables();
	
	//method declaration
	boolean tableExists(String tableName);
}
