package ch.nolix.systemapi.sqlmidschemaapi.querycreatorapi;

/**
 * @author Silvan Wyss
 * @version 2024-12-19
 */
public interface IQueryCreator {

  /**
   * @return a query to get number of tables.
   */
  String createQueryToGetTableCount();

  /**
   * @param tableId
   * @return a query to load the columns of the table with the given tableId.
   */
  String createQueryToLoadCoumnsByTableId(String tableId);

  /**
   * @param tableName
   * @return a query to load the columns of the table with the given tableName.
   */
  String createQueryToLoadColumnsByTableName(String tableName);

  /**
   * @return a query to load the joined columns.
   */
  String createQueryToLoadJoinedColumns();

  /**
   * @param tableName
   * @return a query to load the joined columns by the given tableName.
   */
  String createQueryToLoadJoinedColumns(String tableName);

  /**
   * @return a query to load the schema timestamp.
   */
  String createQueryToLoadSchemaTimestamp();

}
