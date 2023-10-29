//package declaration
package ch.nolix.system.sqldatabasebasicschema.sqlsyntax;

import ch.nolix.systemapi.sqldatabasebasicschemaapi.sqlsyntaxapi.ISchemaQueryCreator;

//class
public final class SchemaQueryCreator implements ISchemaQueryCreator {

  //method
  @Override
  public String createQueryToLoadNameAndDataTypeOfColumns(final String tableName) {
    return "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + tableName + "'";
  }

  //method
  @Override
  public String createQueryToLoadNameOfTables() {
    return "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES";
  }

  //method
  @Override
  public String createQueryToLoadTable(final String tableName) {
    return ("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '" + tableName + "'");
  }

  //method
  @Override
  public String createQueryToLoadTopFirstRecordWhereColumnIsNotNull(
    final String tableName,
    final String columnName) {
    return ("SELECT TOP 1 " + columnName + " FROM " + tableName + " WHERE " + columnName + " NOT NULL");
  }
}
