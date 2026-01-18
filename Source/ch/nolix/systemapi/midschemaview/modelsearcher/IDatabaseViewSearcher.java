/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschemaview.modelsearcher;

import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;
import ch.nolix.systemapi.midschemaview.model.DatabaseViewDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseViewSearcher {
  /**
   * @param databaseView
   * @param tableName
   * @param columnId
   * @return the {@link ColumnViewDto} with the given columnId from the table with
   *         the given tableName from the given databaseView.
   * @throws RuntimeException if the given databaseView does not contain a table
   *                          with the given tableName, that contains a
   *                          {@link ColumnViewDto} with the given columnId.
   */
  ColumnViewDto getColumnViewByTableNameAndColumnId(DatabaseViewDto databaseView, String tableName, String columnId);

  /**
   * @param databaseView
   * @param tableName
   * @param columnName
   * @return the {@link ColumnViewDto} with the given columnName from the table
   *         with the given tableName from the given databaseView.
   * @throws RuntimeException if the given databaseView does not contain a table
   *                          with the given tableName, that contains a
   *                          {@link ColumnViewDto} with the given columnName.
   */
  ColumnViewDto getColumnViewByTableNameAndColumnName(
    DatabaseViewDto databaseView,
    String tableName,
    String columnName);

  /**
   * @param databaseView
   * @param tableId
   * @return the {@link TableViewDto} with the given tableId from the given
   *         databaseView.
   * @throws RuntimeException if the given databaseView does not contain a
   *                          {@link TableViewDto} with the given tableId.
   */
  TableViewDto getTableViewByTableId(DatabaseViewDto databaseView, String tableId);

  /**
   * @param databaseView
   * @param tableName
   * @return the {@link TableViewDto} with the given tableName from the given
   *         databaseView.
   * @throws RuntimeException if the given databaseView does not contain a
   *                          {@link TableViewDto} with the given tableName.
   */
  TableViewDto getTableViewByTableName(DatabaseViewDto databaseView, String tableName);
}
