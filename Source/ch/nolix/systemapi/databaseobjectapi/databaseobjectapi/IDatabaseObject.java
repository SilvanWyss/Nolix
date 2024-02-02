//package
package ch.nolix.systemapi.databaseobjectapi.databaseobjectapi;

//own imports
import ch.nolix.coreapi.functionapi.requestapi.CloseStateRequestable;

//interface
public interface IDatabaseObject extends CloseStateRequestable, DeletionRequestable, NewRequestable {

  //method declaration
  DatabaseObjectState getState();

  //method declaration
  boolean isLinkedWithRealDatabase();
}
