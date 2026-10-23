/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlschema.adapter;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.savecontrol.IResettableChangeSaver;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public interface ISchemaWriter extends IResettableChangeSaver {
  /**
   * Adds the given additionalSqlStatements to the current {@link ISchemaWriter}.
   * 
   * @param additionalSqlStatements
   */
  void addAdditionalSqlStatements(ExtendedIterable<String> additionalSqlStatements);

  /**
   * Adds the given column to the given table.
   * 
   * @param table
   * @param column
   */
  void addColumn(TableIdentification table, ColumnDto column);

  /**
   * Adds the given columns to the given table.
   * 
   * @param table
   * @param columns
   */
  void addColumns(TableIdentification table, ExtendedIterable<ColumnDto> columns);

  /**
   * Adds the given table.
   * 
   * @param table
   */
  void addTable(TableDto table);

  /**
   * Adds the given tables.
   * 
   * @param tables
   */
  void addTables(ExtendedIterable<TableDto> tables);

  /**
   * Adds the given tables.
   * 
   * @param tables
   */
  void addTables(TableDto... tables);

  /**
   * Deletes the column with the given columnName from the table with the given
   * tableName.
   * 
   * @param tableName
   * @param columnName
   */
  void deleteColumn(String tableName, String columnName);

  /**
   * Deletes the column with the given columnName from the table with the given
   * tableName if the column exists.
   * 
   * @param tableName
   * @param columnName
   */
  void deleteColumnIfExists(String tableName, String columnName);

  /**
   * Deletes the table with the given tableName.
   * 
   * @param tableName
   */
  void deleteTable(String tableName);

  /**
   * Renames the column with the given columnName of the table with the given
   * tableName to the given newColumnName.
   * 
   * @param tableName
   * @param columnName
   * @param newColumnName
   */
  void renameColumn(String tableName, String columnName, String newColumnName);

  /**
   * Renames the column with the given columnName of the table with the given
   * tableName to the given newColumnName if the column exists.
   * 
   * @param tableName
   * @param columnName
   * @param newColumnName
   */
  void renameColumnIfExists(String tableName, String columnName, String newColumnName);

  /**
   * Renames the table with the given tableName to the given newTableName.
   * 
   * @param tableName
   * @param newTableName
   */
  void renameTable(String tableName, String newTableName);
}
