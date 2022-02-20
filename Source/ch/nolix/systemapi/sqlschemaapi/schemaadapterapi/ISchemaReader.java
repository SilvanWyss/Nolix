//package declaration
package ch.nolix.systemapi.sqlschemaapi.schemaadapterapi;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.systemapi.sqlschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDTO;

//interface
public interface ISchemaReader extends GroupCloseable {
	
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
