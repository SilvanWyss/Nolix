//package declaration
package ch.nolix.systemapi.rawschemaapi.schemaadapterapi;

//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface ISchemaReader extends GroupCloseable {
	
	//method declaration
	boolean columnIsEmpty(String tableName, String columnName);
	
	//method declaration
	int getTableCount();
	
	//method declaration
	IContainer<IColumnDTO> loadColumnsByTableName(String tableName);
	
	//method declaration
	IContainer<IColumnDTO> loadColumnsByTableId(String tableId);
	
	//method declaration
	IFlatTableDTO loadFlatTableById(String id);
	
	//method declaration
	IFlatTableDTO loadFlatTableByName(String name);
	
	//method declaration
	IContainer<IFlatTableDTO> loadFlatTables();
	
	//method declaration
	ITableDTO loadTableById(String id);
	
	//method declaration
	ITableDTO loadTableByName(String name);
	
	//method declaration
	IContainer<ITableDTO> loadTables();
	
	//method declaration
	ITime loadSchemaTimestamp();
}
