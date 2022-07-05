//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaadapterapi;

import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.IMultiTimeChangeSaver;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface ISchemaAdapter<IMPL> extends IMultiTimeChangeSaver {
	
	//method declaration
	ISchemaAdapter<IMPL> addTable(ITable<IMPL> table);
	
	//method declaration
	ITable<IMPL> getRefTableByName(String name);
	
	//method declaration
	IContainer<ITable<IMPL>> getRefTables();
	
	//method declaration
	int getTableCount();
}
