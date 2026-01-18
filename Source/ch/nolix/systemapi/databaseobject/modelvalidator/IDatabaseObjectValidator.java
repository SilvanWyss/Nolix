/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.databaseobject.modelvalidator;

import ch.nolix.coreapi.resourcecontrol.resourcevalidator.IResourceValidator;
import ch.nolix.systemapi.databaseobject.model.IDatabaseObject;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseObjectValidator extends IResourceValidator {
  void assertIsConnectedWithRealDatabase(IDatabaseObject databaseObject);

  void assertIsLoaded(IDatabaseObject databaseObject);

  void assertIsNew(IDatabaseObject databaseObject);

  void assertIsNotConnectedWithRealDatabase(IDatabaseObject databaseObject);

  void assertIsNotDeleted(IDatabaseObject databaseObject);

  void assertIsNotNew(IDatabaseObject databaseObject);
}
