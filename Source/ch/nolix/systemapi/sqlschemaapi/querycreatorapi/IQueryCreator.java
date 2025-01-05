package ch.nolix.systemapi.sqlschemaapi.querycreatorapi;

public interface IQueryCreator {

  String createQueryToLoadNameAndDataTypeOfColumns(String tableName);

  String createQueryToLoadNameOfTables();

  String createQueryToLoadTable(String tableName);

  String createQueryToLoadTopFirstRecordWhereColumnIsNotNull(String tableName, String columnName);
}
