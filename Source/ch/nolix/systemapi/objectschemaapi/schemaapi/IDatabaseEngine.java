//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.core.container.IContainer;

//interface
public interface IDatabaseEngine<IMPL> {
	
	//method declaration
	IDatabaseEngine<IMPL> addDatabase(IDatabase<IMPL> database);
	
	//method declaration
	IDatabaseEngine<IMPL> createDatabaseWithName(String name);
	
	//method declaration
	IContainer<IDatabase<IMPL>> getRefDatabases();
}
