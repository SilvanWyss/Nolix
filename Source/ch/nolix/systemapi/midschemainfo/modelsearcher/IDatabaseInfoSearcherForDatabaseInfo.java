/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschemainfo.modelsearcher;

import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseInfoSearcherForDatabaseInfo {
  /**
   * @param tableName
   * @param columnId
   * @return the {@link ColumnInfoDto} with the given columnId from the table with
   *         the given tableName from the database view of the current
   *         {@link IDatabaseInfoSearcherForDatabaseInfo}
   * @throws RuntimeException if the database view of the current
   *                          {@link IDatabaseInfoSearcherForDatabaseInfo} does
   *                          not contain a table with the given tableName, that
   *                          contains a {@link ColumnInfoDto} with the given
   *                          columnId.
   */
  ColumnInfoDto getColumnViewByTableNameAndColumnId(String tableName, String columnId);

  /**
   * @param tableName
   * @param columnName
   * @return the {@link ColumnInfoDto} with the given columnName from the table
   *         with the given tableName from the database view of the current
   *         {@link IDatabaseInfoSearcherForDatabaseInfo}
   * @throws RuntimeException if the database view of the current
   *                          {@link IDatabaseInfoSearcherForDatabaseInfo} does
   *                          not contain a table with the given tableName, that
   *                          contains a {@link ColumnInfoDto} with the given
   *                          columnName.
   */
  ColumnInfoDto getColumnViewByTableNameAndColumnName(String tableName, String columnName);

  /**
   * @param tableId
   * @return the {@link TableInfoDto} with the given tableId from database view of
   *         the current {@link IDatabaseInfoSearcherForDatabaseInfo}
   * @throws RuntimeException if the database view of the current
   *                          {@link IDatabaseInfoSearcherForDatabaseInfo} does
   *                          not contain a {@link TableInfoDto} with the given
   *                          tableId.
   */
  TableInfoDto getTableViewByTableId(String tableId);

  /**
   * @param tableName
   * @return the {@link TableInfoDto} with the given tableName from the database
   *         view of the current {@link IDatabaseInfoSearcherForDatabaseInfo}
   * @throws RuntimeException if the database view of the current
   *                          {@link IDatabaseInfoSearcherForDatabaseInfo} does
   *                          not contain a {@link TableInfoDto} with the given
   *                          tableName.
   */
  TableInfoDto getTableViewByTableName(String tableName);
}
