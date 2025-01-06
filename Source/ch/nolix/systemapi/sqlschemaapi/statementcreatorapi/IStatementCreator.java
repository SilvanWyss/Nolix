package ch.nolix.systemapi.sqlschemaapi.statementcreatorapi;

import ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.dto.TableDto;

public interface IStatementCreator {

  String createStatementToAddColumn(String tableName, ColumnDto column);

  String createStatementToAddTable(TableDto table);

  String createStatementToDeleteColumn(String tableName, String columnName);

  String createStatementToDeleteTable(String tableName);

  String createStatementToRenameColumn(String tableName, String columnName, String newColumnName);

  String createStatementToRenameTable(String tableName, String newTableName);
}
