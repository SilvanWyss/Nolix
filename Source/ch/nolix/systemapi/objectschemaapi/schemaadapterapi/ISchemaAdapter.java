//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaadapterapi;

import ch.nolix.core.skilluniversalapi.IMultiTimeChangeSaver;
import ch.nolix.coreapi.containerapi.IContainer;
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
