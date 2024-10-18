package ch.nolix.systemapi.objectdataapi.datavalidatorapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;

public interface IDatabaseValidator {

  void assertCanSaveChanges(IDatabase database);
}
