package ch.nolix.systemapi.objectdataapi.modelexaminerapi;

import ch.nolix.systemapi.databaseobjectapi.modelexaminerapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public interface IEntityExaminer extends IDatabaseObjectExaminer {

  /**
   * @param entity
   * @return true if all new and mandatory fields of the given entity are set,
   *         false otherwise.
   */
  boolean allNewAndEditedMandatoryFieldsAreSet(IEntity entity);

  /**
   * @param entity
   * @return true if the given entity can be deleted, false otherwise.
   */
  boolean canBeDeleted(IEntity entity);

  /**
   * @param entity
   * @return true if the given entity can be inserted into a {@link ITable}, false
   *         otherwise.
   */
  boolean canBeInsertedIntoTable(IEntity entity);

  /**
   * @param entity
   * @return true if the given entity is referenced, false otherwise.
   */
  boolean isReferenced(IEntity entity);

  /**
   * @param entity
   * @return true if the given entity is referenced where the locally deleted
   *         {@link IEntity}s are ignored, false otherwise.
   */
  boolean isReferencedIgnoringLocallyDeletedEntities(IEntity entity);

  /**
   * @param entity
   * @return true if the given entity is referenced in the local data, false
   *         otherwise.
   */
  boolean isReferencedInLocalData(IEntity entity);

  /**
   * @param sourceEntity
   * @param targetEntity
   * @return true if the given sourceEntity referecned the given targetEntity,
   *         false otherwise.
   */
  boolean referencesGivenEntity(IEntity sourceEntity, IEntity targetEntity);
}
