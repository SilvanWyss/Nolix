//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NewArgumentException;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

//class
abstract class SchemaObject implements IDatabaseObject {

  //attribute
  private DatabaseObjectState state = DatabaseObjectState.NEW;

  //method
  @Override
  public final DatabaseObjectState getState() {
    return state;
  }

  //method
  @Override
  public final boolean isClosed() {
    return (getState() == DatabaseObjectState.CLOSED);
  }

  //method
  @Override
  public final boolean isDeleted() {
    return (getState() == DatabaseObjectState.DELETED);
  }

  //method
  @Override
  public final boolean isEdited() {
    return (getState() == DatabaseObjectState.EDITED);
  }

  //method
  @Override
  public final boolean isLoaded() {
    return (getState() == DatabaseObjectState.LOADED);
  }

  //method
  @Override
  public final boolean isNew() {
    return (getState() == DatabaseObjectState.NEW);
  }

  //method declaration
  protected abstract void noteClose();

  //method
  final void internalClose() {

    state = DatabaseObjectState.CLOSED;

    noteClose();
  }

  //method
  final void internalSetDeleted() {
    state = switch (getState()) {
      case NEW ->
        throw NewArgumentException.forArgument(this);
      case LOADED, EDITED ->
        DatabaseObjectState.DELETED;
      case DELETED ->
        throw DeletedArgumentException.forArgument(this);
      case CLOSED ->
        throw ClosedArgumentException.forArgument(this);
      default ->
        throw InvalidArgumentException.forArgument(getState());
    };
  }

  //method
  final void internalSetEdited() {
    switch (getState()) {
      case NEW:
        break;
      case LOADED:
        state = DatabaseObjectState.EDITED;
        break;
      case EDITED:
        break;
      case DELETED:
        throw DeletedArgumentException.forArgument(this);
      case CLOSED:
        throw ClosedArgumentException.forArgument(this);
    }
  }

  //method
  final void internalSetLoaded() {
    state = switch (getState()) {
      case NEW ->
        DatabaseObjectState.LOADED;
      case LOADED ->
        throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already loaded");
      case EDITED ->
        throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already edited");
      case DELETED ->
        throw DeletedArgumentException.forArgument(this);
      case CLOSED ->
        throw ClosedArgumentException.forArgument(this);
    };
  }
}
