/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlschema.adapter;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public interface ISchemaReader extends GroupCloseable {
  /**
   * @param tableName
   * @param columnName
   * @return true if the column with the given columnName of the table with the
   *         given tableName is empty, false otherwise
   */
  boolean columnIsEmpty(String tableName, String columnName);

  /**
   * @return the number of tables
   */
  int getTableCount();

  /**
   * @param tableName
   * @return the table with the given tableName
   * @throws RuntimeException if there is not a table with the given tableName
   */
  TableDto loadTable(String tableName);

  /**
   * @return the tables
   */
  ExtendedIterable<TableDto> loadTables();

  /**
   * @return true if there exists a table, false otherwise
   */
  boolean tableExist();

  /**
   * @param tableName
   * @return true if there exists a table with the given tableName, false
   *         otherwise
   */
  boolean tableExists(String tableName);
}
