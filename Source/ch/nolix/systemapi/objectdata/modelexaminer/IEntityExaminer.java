/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelexaminer;

import ch.nolix.systemapi.database.databaseobjectexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IEntityExaminer extends IDatabaseObjectExaminer<Entity> {
  /**
   * @param entity
   * @return true if all new and mandatory fields of the given entity are set,
   *         false otherwise
   */
  boolean allNewAndEditedMandatoryFieldsAreSet(Entity entity);

  /**
   * @param entity
   * @return true if the given entity can be deleted, false otherwise
   */
  boolean canBeDeleted(Entity entity);

  /**
   * @param entity
   * @return true if the given entity can be inserted into a {@link ITable}, false
   *         otherwise
   */
  boolean canBeInsertedIntoTable(Entity entity);

  /**
   * @param entity
   * @return true if a parent table can be set to the given entity, false
   *         otherwise
   */
  boolean canSetParentTable(Entity entity);

  /**
   * 
   * @param entity
   * @param table
   * @return true if the given table can be set as parent table to the given
   *         entity, false otherwise
   */
  boolean canSetParentTable(Entity entity, ITable<? extends Entity> table);

  /**
   * @param entity
   * @return true if the given entity is referenced ignoring the locally deleted
   *         {@link Entity}s, false otherwise
   */
  boolean isReferencedIgnoringLocallyDeletedEntities(Entity entity);

  /**
   * @param entity
   * @return true if the given entity is referenced in the local data ignoring the
   *         locally deleted {@link Entity}s, false otherwise
   */
  boolean isReferencedInLocalDataIgnoringLocallyDeletedEntities(Entity entity);

  /**
   * @param entity
   * @return true if the given entity is referenced in the persisted data ignoring
   *         the locally deleted {@link Entity}s, false otherwise
   */
  boolean isReferencedInPersistedDataIgnoringLocallyDeletedEntities(Entity entity);

  /**
   * @param sourceEntity
   * @param targetEntity
   * @return true if the given sourceEntity referecned the given targetEntity,
   *         false otherwise
   */
  boolean referencesGivenEntity(Entity sourceEntity, Entity targetEntity);
}
