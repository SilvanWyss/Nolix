//package declaration
package ch.nolix.systemapi.objectdataapi.datavalidatorapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;

//interface
public interface IDatabaseValidator {

  //method declaration
  void assertCanSaveChanges(IDatabase database);
}
