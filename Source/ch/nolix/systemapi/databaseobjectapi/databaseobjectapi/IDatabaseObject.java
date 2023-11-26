//package
package ch.nolix.systemapi.databaseobjectapi.databaseobjectapi;

//own imports
import ch.nolix.coreapi.methodapi.requestapi.CloseStateRequestable;

//interface
public interface IDatabaseObject extends CloseStateRequestable, DeletionRequestable {

  //method declaration
  DatabaseObjectState getState();

  //method declaration
  boolean isLinkedWithRealDatabase();
}
