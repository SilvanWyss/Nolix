//package declaration
package ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi;

//own imports
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IDatabaseObjectTool {

  //method declaration
  boolean isNewOrDeleted(IDatabaseObject databaseObject);

  //method declaration
  boolean isNewOrEdited(IDatabaseObject databaseObject);

  //method declaration
  boolean isNewOrLoaded(IDatabaseObject databaseObject);

  //method declaration
  boolean isNewOrLoadedOrEdited(IDatabaseObject databaseObject);
}
