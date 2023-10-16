//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databasevalidatorapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;

//interface
public interface IEntityValidator {

  //method declaration
  void assertBelongsToTable(IEntity entity);

  //method declaration
  void assertCanBeDeleted(IEntity entity);

  //method declaration
  void assertDoesNotBelongToTable(IEntity entity);

  //method declaration
  void assertHasSaveStamp(IEntity entity);

  //method declaration
  void assertIsNotReferenced(IEntity entity);
}
