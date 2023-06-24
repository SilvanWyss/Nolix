//package declaration
package ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDTO;

//interface
public interface ISchemaReader extends GroupCloseable {
	
	//method declaration
	boolean columnsIsEmpty(String tableName, String columnName);
	
	//method declaration
	IContainer<IColumnDTO> loadColumns(String tableName);
	
	//method declaration
	IContainer<IFlatTableDto> loadFlatTables();
	
	//method declaration
	IContainer<ITableDTO> loadTables();
	
	//method declaration
	boolean tableExists(String tableName);
}
