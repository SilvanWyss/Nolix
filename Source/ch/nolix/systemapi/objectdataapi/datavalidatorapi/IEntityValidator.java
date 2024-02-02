//package declaration
package ch.nolix.systemapi.objectdataapi.datavalidatorapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;

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
