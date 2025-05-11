package ch.nolix.system.sqlschema.mssqlquerycreator;

import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;

public final class MsSqlQueryCreator implements IQueryCreator {

  @Override
  public String createQueryToCountTables() {
    return "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES;";
  }

  @Override
  public String createQueryToCountTables(final String tableName) {
    return "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '" + tableName + "';";
  }

  @Override
  public String createQueryToLoadNameAndDataTypeOfColumns(final String tableName) {
    return "SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + tableName + "';";
  }

  @Override
  public String createQueryToLoadTableNameAndNameAndDataTypeOfColumns() {
    return "SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS;";
  }

  @Override
  public String createQueryToLoadTopFirstRecordWhereColumnIsNotNull(
    final String tableName,
    final String columnName) {
    return ("SELECT TOP 1 " + columnName + " FROM " + tableName + " WHERE " + columnName + " NOT NULL;");
  }
}
