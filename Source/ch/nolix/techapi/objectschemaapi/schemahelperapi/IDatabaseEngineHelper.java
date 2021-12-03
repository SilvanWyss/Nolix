//package declaration
package ch.nolix.techapi.objectschemaapi.schemahelperapi;

//own imports
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabaseEngine;

//interface
public interface IDatabaseEngineHelper {
	
	//method declaration
	void assertDoesNotContainDatabaseWithGivenName(IDatabaseEngine<?> databaseEngine, String name);
	
	//method declaration
	boolean containsDatabaseWithGivenName(IDatabaseEngine<?> databaseEngine, String name);
	
	//method declaration
	int getDatabaseCount(IDatabaseEngine<?> databaseEngine);
	
	//method declaration
	<IMPL> IDatabase<IMPL> getRefDatabaseWithGivenName(IDatabaseEngine<IMPL> databaseEngine, String name);
}
