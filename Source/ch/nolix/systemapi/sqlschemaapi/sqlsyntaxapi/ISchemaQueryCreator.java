package ch.nolix.systemapi.sqlschemaapi.sqlsyntaxapi;

public interface ISchemaQueryCreator {

  String createQueryToLoadNameAndDataTypeOfColumns(String tableName);

  String createQueryToLoadNameOfTables();

  String createQueryToLoadTable(String tableName);

  String createQueryToLoadTopFirstRecordWhereColumnIsNotNull(String tableName, String columnName);
}
