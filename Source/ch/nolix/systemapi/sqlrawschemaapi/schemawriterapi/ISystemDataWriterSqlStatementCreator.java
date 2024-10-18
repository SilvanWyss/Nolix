package ch.nolix.systemapi.sqlrawschemaapi.schemawriterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface ISystemDataWriterSqlStatementCreator {

  String createStatementToAddColumn(String parentTableName, IColumnDto column);

  IContainer<String> createStatementsToAddTable(ITableDto table);

  String createStatementToDeleteColumn(String tableName, String columnName);

  String createStatementToDeleteTable(String tableName);

  String createStatementToSetColumnName(String tableName, String columnName, String newColumnName);

  String createStatementToSetColumnParameterizedFieldType(
    String columnID,
    IParameterizedFieldTypeDto parameterizedFieldType);

  String createStatementToSetSchemaTimestamp(ITime schemaTimestamp);

  String createStatementToSetTableName(String tableName, String newTableName);
}
