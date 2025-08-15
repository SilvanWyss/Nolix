package ch.nolix.systemapi.objectdata.modelvalidator;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

public interface IEntityValidator {

  void assertBelongsToTable(IEntity entity);

  void assertCanBeDeleted(IEntity entity);

  <E extends IEntity> void assertCanSetParentTable(IEntity entity, ITable<E> table);

  void assertDoesNotBelongToTable(IEntity entity);

  void assertHasSaveStamp(IEntity entity);

  void assertIsNotReferenced(IEntity entity);
}
