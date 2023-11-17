//package declaration
package ch.nolix.system.objectdatabase.database;

import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//class
abstract class ImmutableDatabaseObject implements GroupCloseable, IDatabaseObject {

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //method
  @Override
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public final DatabaseObjectState getState() {
    return DatabaseObjectState.LOADED;
  }

  //method
  @Override
  public final boolean isDeleted() {
    return false;
  }

  //method
  @Override
  public final boolean isLinkedWithRealDatabase() {
    return true;
  }

  //method
  @Override
  public final void noteClose() {
  }
}
