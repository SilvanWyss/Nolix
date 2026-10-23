/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlschema.statementcreator;

import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public interface IStatementCreator {
  /**
   * @param tableName
   * @param column
   * @return a statement to add the given column to the table with the given
   *         tableName
   */
  String createStatementToAddColumn(String tableName, ColumnDto column);

  /**
   * @param table
   * @return a statement to add the given table
   */
  String createStatementToAddTable(TableDto table);

  /**
   * @param tableName
   * @param columnName
   * @return a statement to delete the column with the given columnName from the
   *         table with the given tableName
   */
  String createStatementToDeleteColumn(String tableName, String columnName);

  /**
   * @param tableName
   * @param columnName
   * @return a statement to delete the column with the given columnName from the
   *         table with the given tableName if the column exists
   */
  String createStatementToDeleteColumnIfExists(String tableName, String columnName);

  /**
   * @param tableName
   * @return a statement to delete the table with the given tableName
   */
  String createStatementToDeleteTable(String tableName);

  /**
   * @param tableName
   * @param columnName
   * @param newColumnName
   * @return a statement to rename the column with the given columnName of the
   *         table with the given tableName to the given newColumnName
   */
  String createStatementToRenameColumn(String tableName, String columnName, String newColumnName);

  /**
   * @param tableName
   * @param columnName
   * @param newColumnName
   * @return a statement to rename the column with the given columnName of the
   *         table with the given tableName to the given newColumnName if the
   *         column exists
   */
  String createStatementToRenameColumnIfExists(String tableName, String columnName, String newColumnName);

  /**
   * @param tableName
   * @param newTableName
   * @return a statement to rename the table with the given tableName to the given
   *         newTableName
   */
  String createStatementToRenameTable(String tableName, String newTableName);
}
