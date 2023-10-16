//package declaration
package ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi;

//own imports
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IEntityStatementCreator {

  //method declaration
  String createStatementToDeleteEntity(String tableName, IEntityHeadDto entity);

  //method
  String createStatementToExpectGivenSchemaTimestamp(ITime schemaTimestamp);

  //method
  String createStatementToExpectTableContainsEntity(String tableName, String entityId);

  //method declaration
  String createStatementToInsertNewEntity(String tableName, INewEntityDto newEntity);

  //method declaration
  String createStatementToSetEntityAsUpdated(String tableName, IEntityHeadDto entity);

  //method declaration
  String createStatementToUpdateEntityOnTable(String tableName, IEntityUpdateDto entityUpdate);
}
