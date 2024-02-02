//package declaration
package ch.nolix.systemapi.objectdataapi.datavalidatorapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//interface
public interface ITableValidator {

  //method declaration
  void assertCanInsertGivenEntity(ITable<?> table, IEntity entity);
}
