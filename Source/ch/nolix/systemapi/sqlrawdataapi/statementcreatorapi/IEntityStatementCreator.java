package ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi;

import ch.nolix.systemapi.rawdataapi.datadto.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityUpdateDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IEntityStatementCreator {

  String createStatementToDeleteEntity(String tableName, EntityDeletionDto entity);

  String createStatementToDeleteEntityHead(String entityId);

  String createStatementToExpectGivenSchemaTimestamp(ITime schemaTimestamp);

  String createStatementToExpectTableContainsEntity(String tableName, String entityId);

  String createStatementToInsertEntity(String tableName, EntityCreationDto newEntity);

  String createStatementToInsertEntityHead(String tableId, String entityId);

  String createStatementToUpdateEntityOnTable(String tableName, EntityUpdateDto entityUpdate);
}
