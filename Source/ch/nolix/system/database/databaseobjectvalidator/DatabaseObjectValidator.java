/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.database.databaseobjectvalidator;

import ch.nolix.base.resourcecontrol.resourcevalidator.ResourceValidatorUnit;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;
import ch.nolix.systemapi.database.databaseobjectvalidator.IDatabaseObjectValidator;

/**
 * @author Silvan Wyss
 */
public final class DatabaseObjectValidator extends ResourceValidatorUnit implements IDatabaseObjectValidator {
  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsConnectedWithRealDatabase(final DatabaseObject databaseObject) {
    if (!databaseObject.isConnectedWithRealDatabase()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not linked with a real database");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsLoaded(final DatabaseObject databaseObject) {
    if (!databaseObject.isLoaded()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not loaded");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsNew(final DatabaseObject databaseObject) {
    if (!databaseObject.isNew()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not new");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsNotDeleted(final DatabaseObject databaseObject) {
    if (databaseObject.isDeleted()) {
      throw DeletedArgumentException.forArgument(databaseObject);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsNotConnectedWithRealDatabase(final DatabaseObject databaseObject) {
    if (databaseObject.isConnectedWithRealDatabase()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is linked with a real database");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsNotNew(final DatabaseObject databaseObject) {
    if (databaseObject.isNew()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is new");
    }
  }
}
