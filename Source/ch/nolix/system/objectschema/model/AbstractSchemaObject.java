package ch.nolix.system.objectschema.model;

import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NewArgumentException;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.databaseobjectapi.modelapi.IDatabaseObject;

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
    return (getState() == DatabaseObjectState.LOADED);
  }

  @Override
  public final boolean isNew() {
    return (getState() == DatabaseObjectState.NEW);
  }

  protected abstract void noteClose();

  final void internalClose() {

    state = DatabaseObjectState.CLOSED;

    noteClose();
  }

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
