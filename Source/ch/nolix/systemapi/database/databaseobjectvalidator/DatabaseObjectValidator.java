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
  void assertIsConnectedWithRealDatabase(O databaseObject);

  void assertIsLoaded(O databaseObject);

  void assertIsNew(O databaseObject);

  void assertIsNotConnectedWithRealDatabase(O databaseObject);

  void assertIsNotDeleted(O databaseObject);

  void assertIsNotNew(O databaseObject);
}
