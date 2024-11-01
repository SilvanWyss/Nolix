package ch.nolix.system.databaseobject.databaseobjecttool;

import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;

public class DatabaseObjectTool implements IDatabaseObjectTool {

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
