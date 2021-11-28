//package declaration
package ch.nolix.techapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaAdapter;

//interface
public interface IDatabase extends IDatabaseObject, Named {
	
	//method declaration
	IDatabase addTable(ITable table);
	
	//method declaration
	boolean belongsToEngine();
	
	//method declaration
	IDatabase createTableWithName(String name);
	
	//method declaration
	IDatabaseEngine getParentEngine();
	
	//method declaration
	IContainer<ITable> getRefTables();
	
	//method declaration
	void setRealSchemaAdapter(ISchemaAdapter schemaAdapter);
}
