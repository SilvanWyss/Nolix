package ch.nolix.systemapi.sqlrawschemaapi.querycreatorapi;

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
  String createQueryToLoadCoumnsByTableName(String tableName);

  /**
   * @param id
   * @return a query to load the flat table with the given id.
   */
  String createQueryToLoadFlatTableById(String id);

  /**
   * @param name
   * @return a query to load the flat table with the given name.
   */
  String createQueryToLoadFlatTableByName(String name);

  /**
   * @return a query to load the flat tables.
   */
  String createQueryToLoadFlatTables();

  /**
   * @return a query to load the schema timestamp.
   */
  String createQueryToLoadSchemaTimestamp();
}
