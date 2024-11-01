package ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi;

import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IEntityStatementCreator {

  String createStatementToDeleteEntity(String tableName, IEntityHeadDto entity);

  String createStatementToDeleteEntityHead(String entityId);

  String createStatementToExpectGivenSchemaTimestamp(ITime schemaTimestamp);

  String createStatementToExpectTableContainsEntity(String tableName, String entityId);

  String createStatementToInsertEntity(String tableName, INewEntityDto newEntity);

  String createStatementToInsertEntityHead(String tableId, String entityId);

  String createStatementToSetEntityAsUpdated(String tableName, IEntityHeadDto entity);

  String createStatementToUpdateEntityOnTable(String tableName, IEntityUpdateDto entityUpdate);
}
