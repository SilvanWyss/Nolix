//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaadapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.IMultiTimeChangeSaver;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface ISchemaAdapter extends IMultiTimeChangeSaver {
	
	//method declaration
	ISchemaAdapter addTable(ITable table);
	
	//method declaration
	ITable getOriTableByName(String name);
	
	//method declaration
	IContainer<ITable> getOriTables();
	
	//method declaration
	int getTableCount();
}
