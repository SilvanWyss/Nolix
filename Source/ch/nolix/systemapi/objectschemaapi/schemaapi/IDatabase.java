//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;

//interface
public interface IDatabase<IMPL> extends IDatabaseObject, Named {
	
	//method declaration
	IDatabase<IMPL> addTable(ITable<IMPL> table);
	
	//method declaration
	IDatabase<IMPL> createTableWithName(String name);
	
	//method declaration
	IContainer<ITable<IMPL>> getRefTables();
	
	//method declaration
	int getTableCount();
	
	//method declaration
	void setRawSchemaAdapter(ISchemaAdapter rawSchemaAdapter);
}
