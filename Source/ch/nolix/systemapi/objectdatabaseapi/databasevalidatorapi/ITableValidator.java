//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databasevalidatorapi;

//own imports
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//interface
public interface ITableValidator {

  //method declaration
  void assertCanInsertGivenEntity(ITable<?> table, IEntity entity);
}
