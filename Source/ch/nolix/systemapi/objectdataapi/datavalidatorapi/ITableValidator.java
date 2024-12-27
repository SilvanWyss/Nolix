package ch.nolix.systemapi.objectdataapi.datavalidatorapi;

import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public interface ITableValidator {

  void assertCanInsertEntity(ITable<? extends IEntity> table, IEntity entity);
}
