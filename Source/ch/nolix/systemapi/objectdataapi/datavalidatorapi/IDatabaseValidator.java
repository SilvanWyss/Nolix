package ch.nolix.systemapi.objectdataapi.datavalidatorapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IDatabase;

public interface IDatabaseValidator {

  void assertCanSaveChanges(IDatabase database);
}
