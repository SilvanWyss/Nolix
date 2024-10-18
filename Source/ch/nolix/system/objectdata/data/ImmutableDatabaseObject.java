package ch.nolix.system.objectdata.data;

import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;

abstract class ImmutableDatabaseObject implements GroupCloseable, IDatabaseObject {

  private final CloseController closeController = CloseController.forElement(this);

  @Override
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public final DatabaseObjectState getState() {
    return DatabaseObjectState.LOADED;
  }

  @Override
  public final boolean isDeleted() {
    return false;
  }

  @Override
  public final boolean isEdited() {
    return (getState() == DatabaseObjectState.EDITED);
  }

  @Override
  public final boolean isLinkedWithRealDatabase() {
    return true;
  }

  @Override
  public final boolean isLoaded() {
    return (getState() == DatabaseObjectState.LOADED);
  }

  @Override
  public final boolean isNew() {
    return false;
  }

  @Override
  public final void noteClose() {
  }
}
