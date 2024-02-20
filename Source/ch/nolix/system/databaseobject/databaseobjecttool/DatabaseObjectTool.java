//package declaration
package ch.nolix.system.databaseobject.databaseobjecttool;

//own imports
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;

//class
public class DatabaseObjectTool implements IDatabaseObjectTool {

  //method
  @Override
  public final boolean isDeleted(final IDatabaseObject databaseObject) {
    return (databaseObject.getState() == DatabaseObjectState.DELETED);
  }

  //method
  @Override
  public final boolean isEdited(final IDatabaseObject databaseObject) {
    return (databaseObject.getState() == DatabaseObjectState.EDITED);
  }

  //method
  @Override
  public final boolean isLoaded(final IDatabaseObject databaseObject) {
    return (databaseObject.getState() == DatabaseObjectState.LOADED);
  }

  //method
  @Override
  public boolean isOpen(final IDatabaseObject databaseObject) {
    return databaseObject != null
    && databaseObject.isOpen();
  }

  //method
  @Override
  public final boolean isNew(final IDatabaseObject databaseObject) {
    return (databaseObject.getState() == DatabaseObjectState.NEW);
  }

  //method
  @Override
  public boolean isNewOrDeleted(IDatabaseObject databaseObject) {

    final var state = databaseObject.getState();

    return state == DatabaseObjectState.NEW
    || state == DatabaseObjectState.DELETED;
  }

  //method
  @Override
  public final boolean isNewOrEdited(final IDatabaseObject databaseObject) {

    final var state = databaseObject.getState();

    return state == DatabaseObjectState.NEW
    || state == DatabaseObjectState.EDITED;
  }

  //method
  @Override
  public boolean isNewOrLoaded(final IDatabaseObject databaseObject) {

    final var state = databaseObject.getState();

    return state == DatabaseObjectState.NEW
    || state == DatabaseObjectState.LOADED;
  }

  //method
  @Override
  public boolean isNewOrLoadedOrEdited(final IDatabaseObject databaseObject) {

    final var state = databaseObject.getState();

    return state == DatabaseObjectState.NEW
    || state == DatabaseObjectState.LOADED
    || state == DatabaseObjectState.EDITED;
  }
}
