package ch.nolix.systemapi.sqlschema.querycreator;

/**
 * @author Silvan Wyss
 */
public interface IQueryCreator {
  String createQueryToGetTableCount();

  String createQueryToGetTableCount(String tableName);

  String createQueryToLoadNameAndDataTypeOfColumns(String tableName);

  String createQueryToLoadTableNameAndNameAndDataTypeOfColumns();

  String createQueryToLoadTopFirstRecordWhereColumnIsNotNull(String tableName, String columnName);
}
