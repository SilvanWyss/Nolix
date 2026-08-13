/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.model;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NewArgumentException;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;
import ch.nolix.systemapi.database.databaseobject.DatabaseObjectState;

abstract class AbstractSchemaObject implements DatabaseObject {
  private DatabaseObjectState state = DatabaseObjectState.NEW;

  @Override
  public final DatabaseObjectState getState() {
    return state;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isClosed() {
    return (getState() == DatabaseObjectState.CLOSED);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isDeleted() {
    return (getState() == DatabaseObjectState.DELETED);
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
  public final boolean isLoaded() {
    return (getState() == DatabaseObjectState.UNEDITED);
  }

  /**
   * {@inheritDoc}
   */
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
    final var localState = getState();

    switch (localState) {
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
      default:
        throw InvalidArgumentException.forArgument(localState);
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
