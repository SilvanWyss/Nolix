/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.database.databaseobjectexaminer;

import ch.nolix.systemapi.database.databaseobject.DatabaseObject;
import ch.nolix.systemapi.database.databaseobject.DatabaseObjectState;
import ch.nolix.systemapi.database.databaseobjectexaminer.IDatabaseObjectExaminer;

/**
 * @author Silvan Wyss
 * @param <O> the type of the {@link DatabaseObject}s a
 *            {@link AbstractDatabaseObjectExaminer} is for
 */
public abstract class AbstractDatabaseObjectExaminer<O extends DatabaseObject> implements IDatabaseObjectExaminer<O> {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isNewOrDeleted(final O databaseObject) {
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
  public final boolean isNewOrEdited(final O databaseObject) {
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
  public boolean isNewOrEditedOrDeleted(final O databaseObject) {
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
  public boolean isNewOrLoaded(final O databaseObject) {
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
  public boolean isNewOrLoadedOrEdited(final O databaseObject) {
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
