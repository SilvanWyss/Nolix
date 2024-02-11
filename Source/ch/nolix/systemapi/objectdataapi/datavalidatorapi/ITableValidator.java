//package declaration
package ch.nolix.systemapi.objectdataapi.datavalidatorapi;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//interface
public interface ITableValidator {

  //method declaration
  void assertCanInsertEntity(ITable<? extends IEntity> table, IEntity entity);
}
