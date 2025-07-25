package ch.nolix.systemapi.objectdata.datavalidator;

import ch.nolix.systemapi.objectdata.model.IDatabase;

public interface IDatabaseValidator {

  void assertCanSaveChanges(IDatabase database);
}
