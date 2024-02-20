//package declaration
package ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi;

//own imports
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IDatabaseObjectTool {

  //method declaration
  boolean isDeleted(IDatabaseObject databaseObject);

  //method declaration
  boolean isEdited(IDatabaseObject databaseObject);

  //method declaration
  boolean isLoaded(IDatabaseObject databaseObject);

  //method declaration
  boolean isNew(IDatabaseObject databaseObject);

  //method declaration
  boolean isNewOrDeleted(IDatabaseObject databaseObject);

  //method declaration
  boolean isNewOrEdited(IDatabaseObject databaseObject);

  //method declaration
  boolean isNewOrLoaded(IDatabaseObject databaseObject);

  //method declaration
  boolean isNewOrLoadedOrEdited(IDatabaseObject databaseObject);
}
