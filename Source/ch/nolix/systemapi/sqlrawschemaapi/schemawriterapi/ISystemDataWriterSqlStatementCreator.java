package ch.nolix.systemapi.sqlrawschemaapi.schemawriterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.TableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface ISystemDataWriterSqlStatementCreator {

  String createStatementToAddColumn(String parentTableName, ColumnDto column);

  IContainer<String> createStatementsToAddTable(TableDto table);

  String createStatementToDeleteColumn(String tableName, String columnName);

  String createStatementToDeleteTable(String tableName);

  String createStatementToSetColumnName(String tableName, String columnName, String newColumnName);

  String createStatementToSetColumnParameterizedFieldType(
    String columnID,
    IContentModelDto contentModel);

  String createStatementToSetSchemaTimestamp(ITime schemaTimestamp);

  String createStatementToSetTableName(String tableName, String newTableName);
}
