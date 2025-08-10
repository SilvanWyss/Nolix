package ch.nolix.system.objectschema.model;

import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NewArgumentException;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;

abstract class AbstractSchemaObject implements IDatabaseObject {

  private DatabaseObjectState state = DatabaseObjectState.NEW;

  @Override
  public final DatabaseObjectState getState() {
    return state;
  }

  @Override
  public final boolean isClosed() {
    return (getState() == DatabaseObjectState.CLOSED);
  }

  @Override
  public final boolean isDeleted() {
    return (getState() == DatabaseObjectState.DELETED);
  }

  @Override
  public final boolean isEdited() {
    return (getState() == DatabaseObjectState.EDITED);
  }

  @Override
  public final boolean isLoaded() {
    return (getState() == DatabaseObjectState.UNEDITED);
  }

  @Override
  public final boolean isNew() {
    return (getState() == DatabaseObjectState.NEW);
  }

  protected abstract void noteClose();

  final void close() {

    state = DatabaseObjectState.CLOSED;

    noteClose();
  }

  final void setDeleted() {
    state = switch (getState()) {
      case NEW ->
        throw NewArgumentException.forArgument(this);
      case UNEDITED, EDITED ->
        DatabaseObjectState.DELETED;
      case DELETED ->
        throw DeletedArgumentException.forArgument(this);
      case CLOSED ->
        throw ClosedArgumentException.forArgument(this);
      default ->
        throw InvalidArgumentException.forArgument(getState());
    };
  }

  final void setEdited() {
    switch (getState()) {
      case NEW:
        break;
      case UNEDITED:
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

  final void setLoaded() {
    state = switch (getState()) {
      case NEW ->
        DatabaseObjectState.UNEDITED;
      case UNEDITED ->
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
