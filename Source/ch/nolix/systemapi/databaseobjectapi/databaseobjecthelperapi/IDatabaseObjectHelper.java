//package declaration
package ch.nolix.systemapi.databaseobjectapi.databaseobjecthelperapi;

//own imports
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

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
  void assertIsOpen(IDatabaseObject databaseObject);

  //method declaration
  boolean isDeleted(IDatabaseObject databaseObject);

  //method declaration
  boolean isEdited(IDatabaseObject databaseObject);

  //method declaration
  boolean isLoaded(IDatabaseObject databaseObject);

  //method declaration
  boolean isNew(IDatabaseObject databaseObject);

  //method declaration
  boolean isNewOrEdited(IDatabaseObject databaseObject);

  //method declaration
  boolean isNewOrLoaded(IDatabaseObject databaseObject);
}
