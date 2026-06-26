/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.databaseobject.modelvalidator;

import ch.nolix.base.resourcecontrol.resourcevalidator.ResourceValidatorUnit;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.databaseobject.model.DatabaseObject;
import ch.nolix.systemapi.databaseobject.modelvalidator.IDatabaseObjectValidator;

/**
 * @author Silvan Wyss
 */
public class DatabaseObjectValidator extends ResourceValidatorUnit implements IDatabaseObjectValidator {
  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsConnectedWithRealDatabase(final DatabaseObject databaseObject) {
    if (!databaseObject.isConnectedWithRealDatabase()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not linked with a real database");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsLoaded(final DatabaseObject databaseObject) {
    if (!databaseObject.isLoaded()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not loaded");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsNew(final DatabaseObject databaseObject) {
    if (!databaseObject.isNew()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(databaseObject, "is not new");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsNotDeleted(final DatabaseObject databaseObject) {
    if (databaseObject.isDeleted()) {
      throw DeletedArgumentException.forArgument(databaseObject);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsNotConnectedWithRealDatabase(final DatabaseObject databaseObject) {
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
