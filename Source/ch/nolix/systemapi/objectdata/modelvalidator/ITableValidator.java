package ch.nolix.systemapi.objectdata.modelvalidator;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

public interface ITableValidator {
  void assertCanInsertEntity(ITable<? extends IEntity> table, IEntity entity);
}
