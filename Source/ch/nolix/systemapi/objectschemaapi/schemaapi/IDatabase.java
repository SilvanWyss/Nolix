//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.core.container.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//interface
public interface IDatabase<IMPL> extends IDatabaseObject, Named {
	
	//method declaration
	IDatabase<IMPL> addTable(ITable<IMPL> table);
	
	//method declaration
	IDatabase<IMPL> createTableWithName(String name);
	
	//method declaration
	IContainer<ITable<IMPL>> getRefTables();
	
	//method declaration
	void setRealSchemaAdapter(ISchemaAdapter schemaAdapter);
}
