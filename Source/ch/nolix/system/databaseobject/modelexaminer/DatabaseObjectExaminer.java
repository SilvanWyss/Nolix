package ch.nolix.system.databaseobject.modelexaminer;

import ch.nolix.systemapi.databaseobject.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;

public class DatabaseObjectExaminer implements IDatabaseObjectExaminer {

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

  @Override
  public boolean isNewOrLoaded(final IDatabaseObject databaseObject) {

    if (databaseObject == null) {
      return false;
    }

    final var state = databaseObject.getState();

    return //
    state == DatabaseObjectState.NEW
    || state == DatabaseObjectState.LOADED;
  }

  @Override
  public boolean isNewOrLoadedOrEdited(final IDatabaseObject databaseObject) {

    if (databaseObject == null) {
      return false;
    }

    final var state = databaseObject.getState();

    return //
    state == DatabaseObjectState.NEW
    || state == DatabaseObjectState.LOADED
    || state == DatabaseObjectState.EDITED;
  }
}
