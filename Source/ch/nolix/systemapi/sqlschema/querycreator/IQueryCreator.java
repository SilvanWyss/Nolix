/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlschema.querycreator;

/**
 * @author Silvan Wyss
 */
public interface IQueryCreator {
  /**
   * @return a query to get the number of tables
   */
  String createQueryToGetTableCount();

  /**
   * @param tableName
   * @return a query to get the number of tables with the given tableName
   */
  String createQueryToGetTableCount(String tableName);

  /**
   * @param tableName
   * @return a query to load the name and data type of the columns of the table
   *         with the given tableName
   */
  String createQueryToLoadNameAndDataTypeOfColumns(String tableName);

  /**
   * @return a query to load the table name and the name and data type of the
   *         columns of all tables
   */
  String createQueryToLoadTableNameAndNameAndDataTypeOfColumns();

  /**
   * @param tableName
   * @param columnName
   * @return a query to load the first record of the table with the given
   *         tableName whose column with the given columnName is not null
   */
  String createQueryToLoadTopFirstRecordWhereColumnIsNotNull(String tableName, String columnName);
}
