package ch.nolix.system.sqlschema.querycreator;

import ch.nolix.systemapi.sqlschema.querycreator.IQueryCreator;

public final class QueryCreator implements IQueryCreator {

  @Override
  public String createQueryToGetTableCount() {
    return "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES;";
  }

  @Override
  public String createQueryToGetTableCount(final String tableName) {
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
