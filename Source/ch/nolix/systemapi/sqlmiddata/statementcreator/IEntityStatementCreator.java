/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.statementcreator;

import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.middata.model.EntityDeletionDto;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public interface IEntityStatementCreator {
  /**
   * @param tableName
   * @param entity
   * @return a statement to delete the given entity from the table with the given
   *         tableName
   */
  String createStatementToDeleteEntity(String tableName, EntityDeletionDto entity);

  /**
   * @param entityId
   * @return a statement to delete the entity index of the entity with the given
   *         entityId
   */
  String createStatementToDeleteEntityIndex(String entityId);

  /**
   * @param schemaTimestamp
   * @return a statement that expects that the schema has the given
   *         schemaTimestamp
   */
  String createStatementToExpectGivenSchemaTimestamp(ITime schemaTimestamp);

  /**
   * @param tableName
   * @param entityId
   * @return a statement that expects that the table with the given tableName
   *         contains the entity with the given entityId
   */
  String createStatementToExpectTableContainsEntity(String tableName, String entityId);

  /**
   * @param tableName
   * @param newEntity
   * @return a statement to insert the given newEntity into the table with the
   *         given tableName
   */
  String createStatementToInsertEntity(String tableName, EntityCreationDto newEntity);

  /**
   * @param tableId
   * @param entityId
   * @return a statement to insert an entity index for the entity with the given
   *         entityId of the table with the given tableId
   */
  String createStatementToInsertEntityIndex(String tableId, String entityId);

  /**
   * @param tableName
   * @param entityUpdate
   * @return a statement to apply the given entityUpdate on the table with the
   *         given tableName
   */
  String createStatementToUpdateEntityOnTable(String tableName, EntityUpdateDto entityUpdate);
}
