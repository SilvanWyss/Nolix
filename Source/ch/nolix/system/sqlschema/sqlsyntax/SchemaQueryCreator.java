package ch.nolix.system.sqlschema.sqlsyntax;

import ch.nolix.systemapi.sqlschemaapi.sqlsyntaxapi.ISchemaQueryCreator;

public final class SchemaQueryCreator implements ISchemaQueryCreator {

  @Override
  public String createQueryToLoadNameAndDataTypeOfColumns(final String tableName) {
    return "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + tableName + "'";
  }

  @Override
  public String createQueryToLoadNameOfTables() {
    return "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES";
  }

  @Override
  public String createQueryToLoadTable(final String tableName) {
    return ("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '" + tableName + "'");
  }

  @Override
  public String createQueryToLoadTopFirstRecordWhereColumnIsNotNull(
    final String tableName,
    final String columnName) {
    return ("SELECT TOP 1 " + columnName + " FROM " + tableName + " WHERE " + columnName + " NOT NULL");
  }
}
