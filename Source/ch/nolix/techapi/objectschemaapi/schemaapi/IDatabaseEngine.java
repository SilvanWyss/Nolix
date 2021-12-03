//package declaration
package ch.nolix.techapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IDatabaseEngine<IMPL> {
	
	//method declaration
	IDatabaseEngine<IMPL> addDatabase(IDatabase<IMPL> database);
	
	//method declaration
	IDatabaseEngine<IMPL> createDatabaseWithName(String name);
	
	//method declaration
	IContainer<IDatabase<IMPL>> getRefDatabases();
}
