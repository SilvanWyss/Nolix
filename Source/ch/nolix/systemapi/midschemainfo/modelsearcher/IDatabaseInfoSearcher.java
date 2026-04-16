/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschemainfo.modelsearcher;

import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.DatabaseInfoDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseInfoSearcher {
  /**
   * @param databaseView
   * @param tableName
   * @param columnId
   * @return the {@link ColumnInfoDto} with the given columnId from the table with
   *         the given tableName from the given databaseView.
   * @throws RuntimeException if the given databaseView does not contain a table
   *                          with the given tableName, that contains a
   *                          {@link ColumnInfoDto} with the given columnId.
   */
  ColumnInfoDto getColumnViewByTableNameAndColumnId(DatabaseInfoDto databaseView, String tableName, String columnId);

  /**
   * @param databaseView
   * @param tableName
   * @param columnName
   * @return the {@link ColumnInfoDto} with the given columnName from the table
   *         with the given tableName from the given databaseView.
   * @throws RuntimeException if the given databaseView does not contain a table
   *                          with the given tableName, that contains a
   *                          {@link ColumnInfoDto} with the given columnName.
   */
  ColumnInfoDto getColumnViewByTableNameAndColumnName(
    DatabaseInfoDto databaseView,
    String tableName,
    String columnName);

  /**
   * @param databaseView
   * @param tableId
   * @return the {@link TableInfoDto} with the given tableId from the given
   *         databaseView.
   * @throws RuntimeException if the given databaseView does not contain a
   *                          {@link TableInfoDto} with the given tableId.
   */
  TableInfoDto getTableViewByTableId(DatabaseInfoDto databaseView, String tableId);

  /**
   * @param databaseView
   * @param tableName
   * @return the {@link TableInfoDto} with the given tableName from the given
   *         databaseView.
   * @throws RuntimeException if the given databaseView does not contain a
   *                          {@link TableInfoDto} with the given tableName.
   */
  TableInfoDto getTableViewByTableName(DatabaseInfoDto databaseView, String tableName);
}
