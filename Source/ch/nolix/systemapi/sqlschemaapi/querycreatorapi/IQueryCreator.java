package ch.nolix.systemapi.sqlschemaapi.querycreatorapi;

public interface IQueryCreator {

  String createQueryToCountTables();

  String createQueryToLoadNameAndDataTypeOfColumns(String tableName);

  String createQueryToLoadTable(String tableName);

  String createQueryToLoadTableNameAndNameAndDataTypeOfColumns();

  String createQueryToLoadTopFirstRecordWhereColumnIsNotNull(String tableName, String columnName);
}
