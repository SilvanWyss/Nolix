//package declaration
package ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi;

//own imports
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IEntityStatementCreator {

  //method declaration
  String createStatementToDeleteEntity(String tableName, IEntityHeadDto entity);

  //method declaration
  String createStatementToDeleteEntityHead(String entityId);

  //method declaration
  String createStatementToExpectGivenSchemaTimestamp(ITime schemaTimestamp);

  //method declaration
  String createStatementToExpectTableContainsEntity(String tableName, String entityId);

  //method declaration
  String createStatementToInsertEntity(String tableName, INewEntityDto newEntity);

  //method declaration
  String createStatementToInsertEntityHead(String tableId, String entityId);

  //method declaration
  String createStatementToSetEntityAsUpdated(String tableName, IEntityHeadDto entity);

  //method declaration
  String createStatementToUpdateEntityOnTable(String tableName, IEntityUpdateDto entityUpdate);
}
