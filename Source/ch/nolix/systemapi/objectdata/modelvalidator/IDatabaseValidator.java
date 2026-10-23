/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelvalidator;

import ch.nolix.systemapi.objectdata.model.IDatabase;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseValidator {
  /**
   * @param database
   * @throws RuntimeException if the given database cannot save its changes
   */
  void assertCanSaveChanges(IDatabase database);
}
