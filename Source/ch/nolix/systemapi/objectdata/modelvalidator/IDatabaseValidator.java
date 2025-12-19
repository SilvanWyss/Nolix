package ch.nolix.systemapi.objectdata.modelvalidator;

import ch.nolix.systemapi.objectdata.model.IDatabase;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseValidator {
  void assertCanSaveChanges(IDatabase database);
}
