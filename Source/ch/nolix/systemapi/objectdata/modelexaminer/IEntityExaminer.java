package ch.nolix.systemapi.objectdata.modelexaminer;

import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

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
   * @return true if a parent table can be set to the given entity, false
   *         otherwise.
   */
  boolean canSetParentTable(IEntity entity);

  /**
   * 
   * @param entity
   * @param table
   * @param <E>    is the type of the {@link IEntity}s of the givne table.
   * @return true if the given table can be set as parent table to the given
   *         entity, false otherwise.
   */
  <E extends IEntity> boolean canSetParentTable(IEntity entity, ITable<E> table);

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
