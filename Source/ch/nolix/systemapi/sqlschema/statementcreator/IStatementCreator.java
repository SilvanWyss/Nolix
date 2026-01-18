/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlschema.statementcreator;

import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public interface IStatementCreator {
  String createStatementToAddColumn(String tableName, ColumnDto column);

  String createStatementToAddTable(TableDto table);

  String createStatementToDeleteColumn(String tableName, String columnName);

  String createStatementToDeleteColumnIfExists(String tableName, String columnName);

  String createStatementToDeleteTable(String tableName);

  String createStatementToRenameColumn(String tableName, String columnName, String newColumnName);

  String createStatementToRenameColumnIfExists(String tableName, String columnName, String newColumnName);

  String createStatementToRenameTable(String tableName, String newTableName);
}
