/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.database.databaseobjectvalidator;

import ch.nolix.baseapi.resourcecontrol.resourcevalidator.IResourceValidator;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseObjectValidator extends IResourceValidator {
  void assertIsConnectedWithRealDatabase(DatabaseObject databaseObject);

  void assertIsLoaded(DatabaseObject databaseObject);

  void assertIsNew(DatabaseObject databaseObject);

  void assertIsNotConnectedWithRealDatabase(DatabaseObject databaseObject);

  void assertIsNotDeleted(DatabaseObject databaseObject);

  void assertIsNotNew(DatabaseObject databaseObject);
}
