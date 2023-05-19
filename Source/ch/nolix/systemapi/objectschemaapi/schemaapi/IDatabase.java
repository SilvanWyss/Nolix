//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaAdapter;

//interface
public interface IDatabase extends IDatabaseObject, Named {
	
	//method declaration
	IDatabase addTable(ITable table);
	
	//method declaration
	IDatabase createTableWithName(String name);
	
	//method declaration
	IContainer<ITable> getOriTables();
	
	//method declaration
	int getTableCount();
	
	//method declaration
	void setRawSchemaAdapter(ISchemaAdapter rawSchemaAdapter);
}
