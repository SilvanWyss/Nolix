//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

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
  public final boolean isEdited() {
    return (getState() == DatabaseObjectState.EDITED);
  }

  //method
  @Override
  public final boolean isLinkedWithRealDatabase() {
    return true;
  }

  //method
  @Override
  public final boolean isLoaded() {
    return (getState() == DatabaseObjectState.LOADED);
  }

  //method
  @Override
  public final boolean isNew() {
    return false;
  }

  //method
  @Override
  public final void noteClose() {
  }
}
