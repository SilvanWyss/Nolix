package ch.nolix.systemapi.sqlrawschemaapi.statementcreatorapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.dto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.TableDto;

public interface ISchemaStatementCreator {

  String createStatementToAddColumn(String parentTableName, ColumnDto column);

  IContainer<String> createStatementsToAddTable(TableDto table);

  String createStatementToDeleteColumn(String tableName, String columnName);

  String createStatementToDeleteTable(String tableName);

  String createStatementToSetColumnName(String tableName, String columnName, String newColumnName);

  String createStatementToSetColumnContentModel(String columnId, IContentModelDto contentModel);

  String createStatementToSetTableName(String tableName, String newTableName);
}
