package ch.nolix.systemapi.sqlschemaapi.querycreatorapi;

public interface IQueryCreator {

  String createQueryToCountTables();

  String createQueryToCountTables(String tableName);

  String createQueryToLoadNameAndDataTypeOfColumns(String tableName);

  String createQueryToLoadTableNameAndNameAndDataTypeOfColumns();

  String createQueryToLoadTopFirstRecordWhereColumnIsNotNull(String tableName, String columnName);
}
