package ch.nolix.systemapi.sqlmiddata.statementcreator;

import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.middata.model.EntityDeletionDto;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 */
public interface IEntityStatementCreator {
  String createStatementToDeleteEntity(String tableName, EntityDeletionDto entity);

  String createStatementToDeleteEntityIndex(String entityId);

  String createStatementToExpectGivenSchemaTimestamp(ITime schemaTimestamp);

  String createStatementToExpectTableContainsEntity(String tableName, String entityId);

  String createStatementToInsertEntity(String tableName, EntityCreationDto newEntity);

  String createStatementToInsertEntityIndex(String tableId, String entityId);

  String createStatementToUpdateEntityOnTable(String tableName, EntityUpdateDto entityUpdate);
}
