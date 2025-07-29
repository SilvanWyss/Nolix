package ch.nolix.system.objectdata.model;

import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.systemapi.databaseobject.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

abstract class AbstractImmutableDatabaseObject implements GroupCloseable, IDatabaseObject {

  private final ICloseController closeController = CloseController.forElement(this);

  @Override
  public final ICloseController getStoredCloseController() {
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
  public final boolean isConnectedWithRealDatabase() {
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
