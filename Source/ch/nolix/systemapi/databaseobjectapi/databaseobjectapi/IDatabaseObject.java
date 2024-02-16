//package
package ch.nolix.systemapi.databaseobjectapi.databaseobjectapi;

import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;

//interface
public interface IDatabaseObject extends CloseStateRequestable, DeletionRequestable, NewRequestable {

  //method declaration
  DatabaseObjectState getState();

  //method declaration
  boolean isLinkedWithRealDatabase();
}
