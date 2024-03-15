//package
package ch.nolix.systemapi.databaseobjectapi.databaseobjectapi;

//own imports
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;

//interface
public interface IDatabaseObject
extends CloseStateRequestable, DeletionRequestable, EditedRequestable, LoadedRequestable, NewRequestable {

  //method declaration
  DatabaseObjectState getState();

  //method declaration
  boolean isLinkedWithRealDatabase();
}
