package ch.nolix.systemapi.objectdata.modelvalidator;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

public interface IEntityValidator {
  void assertBelongsToTable(IEntity entity);

  void assertCanBeDeleted(IEntity entity);

  void assertCanSetParentTable(IEntity entity, ITable<? extends IEntity> table);

  void assertDoesNotBelongToTable(IEntity entity);

  void assertHasSaveStamp(IEntity entity);

  void assertIsNotReferenced(IEntity entity);
}
