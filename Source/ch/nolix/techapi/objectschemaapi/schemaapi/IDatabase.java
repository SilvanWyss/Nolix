//package declaration
package ch.nolix.techapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//interface
public interface IDatabase<IMPL> extends IDatabaseObject, Named {
	
	//method declaration
	IDatabase<IMPL> addTable(ITable<IMPL> table);
	
	//method declaration
	boolean belongsToEngine();
	
	//method declaration
	IDatabase<IMPL> createTableWithName(String name);
	
	//method declaration
	IDatabaseEngine<IMPL> getParentEngine();
	
	//method declaration
	IContainer<ITable<IMPL>> getRefTables();
	
	//method declaration
	void setRealSchemaAdapter(ISchemaAdapter schemaAdapter);
}
