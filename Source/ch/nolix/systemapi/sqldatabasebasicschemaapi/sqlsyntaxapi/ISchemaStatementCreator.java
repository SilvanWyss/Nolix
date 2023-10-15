//package declaration
package ch.nolix.systemapi.sqldatabasebasicschemaapi.sqlsyntaxapi;

import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDto;

//interface
public interface ISchemaStatementCreator {

  // method declaration
  String createStatementToAddColumn(String tableName, IColumnDto column);

  // method declaration
  String createStatementToAddTable(ITableDto table);

  // method declaration
  String createStatementToDeleteColumn(String tableName, String columnName);

  // method declaration
  String createStatementToDeleteTable(String tableName);

  // method declaration
  String createStatementToRenameColumn(String tableName, String columnName, String newColumnName);

  // method declaration
  String createStatementToRenameTable(String tableName, String newTableName);
}
