package ch.nolix.systemapi.sqlmidschema.statementcreator;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.IContentModelDto;
import ch.nolix.systemapi.midschema.model.TableDto;

public interface IMetaDataStatementCreator {
  String createStatementToAddColumn(String tableName, ColumnDto column);

  String createStatementToAddTable(String tableId, String tableName);

  IContainer<String> createStatementsToAddTable(TableDto table);

  String createStatementToDeleteColumn(String tableName, String columnName);

  String createStatementToDeleteTable(String tableName);

  String createStatementToRenameColumn(String tableName, String columnName, String newColumnName);

  String createStatementToRenameTable(String tableName, String newTableName);

  String createStatementToSetContentModel(String tableName, String columnName, IContentModelDto contentModel);
}
