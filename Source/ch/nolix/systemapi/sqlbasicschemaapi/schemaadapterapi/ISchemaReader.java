//package declaration
package ch.nolix.systemapi.sqlbasicschemaapi.schemaadapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
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
