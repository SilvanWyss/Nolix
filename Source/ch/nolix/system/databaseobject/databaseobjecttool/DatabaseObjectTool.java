//package declaration
package ch.nolix.system.databaseobject.databaseobjecttool;

//own imports
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;

//class
public class DatabaseObjectTool implements IDatabaseObjectTool {

  //method
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

  //method
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

  //method
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

  //method
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
