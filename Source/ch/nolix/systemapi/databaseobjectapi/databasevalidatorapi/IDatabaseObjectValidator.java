//package declaration
package ch.nolix.systemapi.databaseobjectapi.databasevalidatorapi;

//own imports
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IDatabaseObjectValidator {

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
  void assertIsOpen(IDatabaseObject databaseObject);
}
