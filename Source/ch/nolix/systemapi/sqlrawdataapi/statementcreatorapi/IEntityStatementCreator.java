package ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi;

import ch.nolix.systemapi.rawdataapi.model.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.model.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.model.EntityUpdateDto;
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
