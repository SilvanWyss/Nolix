package ch.nolix.systemapi.sqlmidschemaapi.statementcreatorapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.midschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;

public interface ISchemaStatementCreator {

  String createStatementToAddColumn(String tableName, ColumnDto column);

  String createStatementToAddTable(String tableId, String tableName);

  IContainer<String> createStatementsToAddTable(TableDto table);

  String createStatementToDeleteColumn(String tableName, String columnName);

  String createStatementToDeleteTable(String tableName);

  String createStatementToRenameColumn(String tableName, String columnName, String newColumnName);

  String createStatementToRenameTable(String tableName, String newTableName);

  String createStatementToSetContentModel(String tableName, String columnName, IContentModelDto contentModel);
}
