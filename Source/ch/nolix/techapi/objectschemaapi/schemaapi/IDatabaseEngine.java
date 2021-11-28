//package declaration
package ch.nolix.techapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IDatabaseEngine {
	
	//method declaration
	IDatabaseEngine addDatabase(IDatabase database);
	
	//method declaration
	IDatabaseEngine createDatabaseWithName(String name);
	
	//method declaration
	IContainer<IDatabase> getRefDatabases();
}
