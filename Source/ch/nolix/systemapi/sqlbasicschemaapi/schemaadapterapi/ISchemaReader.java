//package declaration
package ch.nolix.systemapi.sqlbasicschemaapi.schemaadapterapi;

//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.systemapi.sqlbasicschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.ITableDTO;

//interface
public interface ISchemaReader extends GroupCloseable {
	
	//method declaration
	boolean columnsIsEmpty(String tableName, String columnName);
	
	//method declaration
	IContainer<IColumnDTO> loadColumns(String tableName);
	
	//method declaration
	IContainer<IFlatTableDTO> loadFlatTables();
	
	//method declaration
	IContainer<ITableDTO> loadTables();
	
	//method declaration
	boolean tableExists(String tableName);
}
