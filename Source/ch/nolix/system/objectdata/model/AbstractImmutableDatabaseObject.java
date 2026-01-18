/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;

abstract class AbstractImmutableDatabaseObject implements GroupCloseable, IDatabaseObject {
  private final ICloseController closeController = CloseController.forElement(this);

  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final DatabaseObjectState getState() {
    return DatabaseObjectState.UNEDITED;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isDeleted() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isEdited() {
    return (getState() == DatabaseObjectState.EDITED);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isConnectedWithRealDatabase() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isLoaded() {
    return (getState() == DatabaseObjectState.UNEDITED);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isNew() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
    //Does nothing.
  }
}
