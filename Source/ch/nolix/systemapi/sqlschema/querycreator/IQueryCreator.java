package ch.nolix.systemapi.sqlschema.querycreator;

public interface IQueryCreator {

  String createQueryToGetTableCount();

  String createQueryToGetTableCount(String tableName);

  String createQueryToLoadNameAndDataTypeOfColumns(String tableName);

  String createQueryToLoadTableNameAndNameAndDataTypeOfColumns();

  String createQueryToLoadTopFirstRecordWhereColumnIsNotNull(String tableName, String columnName);
}
