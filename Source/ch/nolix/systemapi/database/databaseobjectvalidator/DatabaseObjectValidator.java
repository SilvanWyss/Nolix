/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.database.databaseobjectvalidator;

import ch.nolix.baseapi.resourcecontrol.resourcevalidator.IResourceValidator;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;

/**
 * @author Silvan Wyss
 * @param <O> the type of the {@link DatabaseObject}s a
 *            {@link DatabaseObjectValidator} validates
 */
public interface DatabaseObjectValidator<O extends DatabaseObject> extends IResourceValidator {
  /**
   * @param databaseObject
   * @throws RuntimeException if the given databaseObject is not connected with a
   *                          real database
   */
  void assertIsConnectedWithRealDatabase(O databaseObject);

  /**
   * @param databaseObject
   * @throws RuntimeException if the given databaseObject is not loaded
   */
  void assertIsLoaded(O databaseObject);

  /**
   * @param databaseObject
   * @throws RuntimeException if the given databaseObject is not new
   */
  void assertIsNew(O databaseObject);

  /**
   * @param databaseObject
   * @throws RuntimeException if the given databaseObject is connected with a real
   *                          database
   */
  void assertIsNotConnectedWithRealDatabase(O databaseObject);

  /**
   * @param databaseObject
   * @throws RuntimeException if the given databaseObject is deleted
   */
  void assertIsNotDeleted(O databaseObject);

  /**
   * @param databaseObject
   * @throws RuntimeException if the given databaseObject is new
   */
  void assertIsNotNew(O databaseObject);
}
