//package declaration
package ch.nolix.systemapi.sqlrawschemaapi.schemawriterapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface ISystemDataWriterSqlStatementCreator {

  //method declaration
  String createStatementToAddColumn(String parentTableName, IColumnDto column);

  //method declaration
  IContainer<String> createStatementsToAddTable(ITableDto table);

  //method declaration
  String createStatementToDeleteColumn(String tableName, String columnName);

  //method declaration
  String createStatementToDeleteTable(String tableName);

  //method declaration
  String createStatementToSetColumnName(String tableName, String columnName, String newColumnName);

  //method declaration
  String createStatementToSetColumnParameterizedFieldType(
    String columnID,
    IParameterizedFieldTypeDto parameterizedFieldType);

  //method declaration
  String createStatementToSetSchemaTimestamp(ITime schemaTimestamp);

  //method declaration
  String createStatementToSetTableName(String tableName, String newTableName);
}
