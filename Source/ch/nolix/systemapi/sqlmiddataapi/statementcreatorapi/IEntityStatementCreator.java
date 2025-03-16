package ch.nolix.systemapi.sqlmiddataapi.statementcreatorapi;

import ch.nolix.systemapi.middataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IEntityStatementCreator {

  String createStatementToDeleteEntity(String tableName, EntityDeletionDto entity);

  String createStatementToDeleteEntityIndex(String entityId);

  String createStatementToExpectGivenSchemaTimestamp(ITime schemaTimestamp);

  String createStatementToExpectTableContainsEntity(String tableName, String entityId);

  String createStatementToInsertEntity(String tableName, EntityCreationDto newEntity);

  String createStatementToInsertEntityIndex(String tableId, String entityId);

  String createStatementToUpdateEntityOnTable(String tableName, EntityUpdateDto entityUpdate);
}
