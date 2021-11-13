//package declaration
package ch.nolix.techapi.databaseapi.databaseobjecthelperapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IDatabaseObjectHelper {
	
	//method declaration
	void assertIsLinkedWithRealDatabase(IDatabaseObject databaseObject);
	
	//method declaration
	void assertIsLoaded(IDatabaseObject databaseObject);
	
	//method declaration
	void assertIsNew(IDatabaseObject databaseObject);
	
	//method declaration
	void assertIsNotDeleted(IDatabaseObject databaseObject);
	
	//method declaration
	void assertIsNotLinkedWithRealDatabase(IDatabaseObject databaseObject);
	
	//method declaration
	void assertIsNotNew(IDatabaseObject databaseObject);
	
	//method declaration
	boolean isDeleted(IDatabaseObject databaseObject);
	
	//method declaration
	boolean isLoaded(IDatabaseObject databaseObject);
	
	//method declaration
	boolean isNew(IDatabaseObject databaseObject);
}
