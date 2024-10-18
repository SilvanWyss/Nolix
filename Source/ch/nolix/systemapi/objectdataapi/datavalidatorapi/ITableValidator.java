package ch.nolix.systemapi.objectdataapi.datavalidatorapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

public interface ITableValidator {

  void assertCanInsertEntity(ITable<? extends IEntity> table, IEntity entity);
}
