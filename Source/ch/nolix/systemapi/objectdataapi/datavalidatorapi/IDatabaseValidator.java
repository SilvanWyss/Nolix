//package declaration
package ch.nolix.systemapi.objectdataapi.datavalidatorapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;

//interface
public interface IDatabaseValidator {

  //method declaration
  void assertCanSaveChanges(IDatabase database);
}
