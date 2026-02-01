/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.databaseobject.modelexaminer;

import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;

/**
 * @author Silvan Wyss
 */
public class DatabaseObjectExaminer implements IDatabaseObjectExaminer {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isNewOrDeleted(IDatabaseObject databaseObject) {
    if (databaseObject == null) {
      return false;
    }

    final var state = databaseObject.getState();

    return //
    state == DatabaseObjectState.NEW
    || state == DatabaseObjectState.DELETED;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isNewOrEdited(final IDatabaseObject databaseObject) {
    if (databaseObject == null) {
      return false;
    }

    final var state = databaseObject.getState();

    return //
    state == DatabaseObjectState.NEW
    || state == DatabaseObjectState.EDITED;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isNewOrEditedOrDeleted(final IDatabaseObject databaseObject) {
    if (databaseObject == null) {
      return false;
    }

    return //
    switch (databaseObject.getState()) {
      case NEW, EDITED, DELETED -> true;
      default -> false;
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isNewOrLoaded(final IDatabaseObject databaseObject) {
    if (databaseObject == null) {
      return false;
    }

    final var state = databaseObject.getState();

    return //
    state == DatabaseObjectState.NEW
    || state == DatabaseObjectState.UNEDITED;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isNewOrLoadedOrEdited(final IDatabaseObject databaseObject) {
    if (databaseObject == null) {
      return false;
    }

    final var state = databaseObject.getState();

    return //
    state == DatabaseObjectState.NEW
    || state == DatabaseObjectState.UNEDITED
    || state == DatabaseObjectState.EDITED;
  }
}
