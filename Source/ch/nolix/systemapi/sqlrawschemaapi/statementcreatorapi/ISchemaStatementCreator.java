package ch.nolix.systemapi.sqlrawschemaapi.statementcreatorapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;

public interface ISchemaStatementCreator {

  String createStatementToAddColumn(String tableName, ColumnDto column);

  String createStatementToAddTable(String tableId, String tableName);

  IContainer<String> createStatementsToAddTable(TableDto table);

  String createStatementToDeleteColumn(String tableName, String columnName);

  String createStatementToDeleteTable(String tableName);

  String createStatementToSetColumnName(String tableName, String columnName, String newColumnName);

  String createStatementToSetContentModel(String tableName, String columnName, IContentModelDto contentModel);

  String createStatementToSetTableName(String tableName, String newTableName);
}
