package ch.nolix.systemapi.objectdataapi.datavalidatorapi;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;

public interface IEntityValidator {

  void assertBelongsToTable(IEntity entity);

  void assertCanBeDeleted(IEntity entity);

  void assertDoesNotBelongToTable(IEntity entity);

  void assertHasSaveStamp(IEntity entity);

  void assertIsNotReferenced(IEntity entity);
}
