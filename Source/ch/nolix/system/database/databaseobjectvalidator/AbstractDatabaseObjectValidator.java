/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.database.databaseobjectvalidator;

import ch.nolix.base.resourcecontrol.resourcevalidator.AbstractResourceValidator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;
import ch.nolix.systemapi.database.databaseobjectvalidator.DatabaseObjectValidator;

/**
 * @author Silvan Wyss
 * @param <O> the type of the {@link DatabaseObject}s a
 *            {@link AbstractDatabaseObjectValidator} validates
 */
public abstract class AbstractDatabaseObjectValidator<O extends DatabaseObject>
extends AbstractResourceValidator
implements DatabaseObjectValidator<O> {
  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsConnectedWithRealDatabase(final O databaseObject) {
    if (!databaseObject.isConnectedWithRealDatabase()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not linked with a real database");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsLoaded(final O databaseObject) {
    if (!databaseObject.isLoaded()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not loaded");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsNew(final O databaseObject) {
    if (!databaseObject.isNew()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not new");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsNotDeleted(final O databaseObject) {
    if (databaseObject.isDeleted()) {
      throw DeletedArgumentException.forArgument(databaseObject);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsNotConnectedWithRealDatabase(final O databaseObject) {
    if (databaseObject.isConnectedWithRealDatabase()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is linked with a real database");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsNotNew(final DatabaseObject databaseObject) {
    if (databaseObject.isNew()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is new");
    }
  }
}
