//package
package ch.nolix.systemapi.databaseapi.databaseobjectapi;

import ch.nolix.coreapi.functionapi.requestapi.CloseStateRequestable;

//interface
public interface IDatabaseObject extends CloseStateRequestable, DeletionRequestable {

  //method declaration
  DatabaseObjectState getState();

  //method declaration
  boolean isLinkedWithRealDatabase();
}
