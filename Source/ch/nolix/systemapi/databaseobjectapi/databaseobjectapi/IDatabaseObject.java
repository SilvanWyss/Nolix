//package
package ch.nolix.systemapi.databaseobjectapi.databaseobjectapi;

//own imports
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;

//interface
public interface IDatabaseObject extends CloseStateRequestable, DeletionRequestable, LoadedRequestable, NewRequestable {

  //method declaration
  DatabaseObjectState getState();

  //method declaration
  boolean isLinkedWithRealDatabase();
}
