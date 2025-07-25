package ch.nolix.systemapi.objectdata.datavalidator;

import ch.nolix.systemapi.objectdata.model.IEntity;

public interface IEntityValidator {

  void assertBelongsToTable(IEntity entity);

  void assertCanBeDeleted(IEntity entity);

  void assertDoesNotBelongToTable(IEntity entity);

  void assertHasSaveStamp(IEntity entity);

  void assertIsNotReferenced(IEntity entity);
}
