//package declaration
package ch.nolix.system.databaseadapter;

//interface
public interface IDatabaseAdapterCreator {
	
	//method declaration
	DatabaseAdapter createDatabaseAdapter(Schema schema);
}
