//package declaration
package ch.nolix.system.database.databaseadapter;

//interface
public interface IDatabaseAdapterCreator {
	
	//method declaration
	DatabaseAdapter createDatabaseAdapter(Schema schema);
}
