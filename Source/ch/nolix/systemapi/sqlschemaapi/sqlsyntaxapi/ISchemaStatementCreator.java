package ch.nolix.systemapi.sqlschemaapi.sqlsyntaxapi;

import ch.nolix.systemapi.sqlschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

public interface ISchemaStatementCreator {

  String createStatementToAddColumn(String tableName, ColumnDto column);

  String createStatementToAddTable(ITableDto table);

  String createStatementToDeleteColumn(String tableName, String columnName);

  String createStatementToDeleteTable(String tableName);

  String createStatementToRenameColumn(String tableName, String columnName, String newColumnName);

  String createStatementToRenameTable(String tableName, String newTableName);
}
