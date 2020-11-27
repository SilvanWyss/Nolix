//package declaration
package ch.nolix.system.databaseAdapter;

//interface
public interface IDatabaseAdapterCreator {
	
	//method declaration
	DatabaseAdapter createDatabaseAdapter(Schema schema);
}
