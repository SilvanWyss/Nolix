//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databasevalidatorapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IDatabase;

//interface
public interface IDatabaseValidator {

  // method declaration
  void assertCanSaveChanges(IDatabase database);
}
