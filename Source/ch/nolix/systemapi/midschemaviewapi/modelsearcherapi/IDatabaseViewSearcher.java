package ch.nolix.systemapi.midschemaviewapi.modelsearcherapi;

import ch.nolix.systemapi.midschemaviewapi.modelapi.ColumnViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.DatabaseViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-06-19
 */
public interface IDatabaseViewSearcher {

  /**
   * @param databaseView
   * @param columnId
   * @return the {@link ColumnViewDto} with the given columnId from the given
   *         databaseView.
   * @throws RuntimeException if the given databaseView does not contain a
   *                          {@link ColumnViewDto} with the given columnId.
   */
  ColumnViewDto getColumnViewByColumnId(DatabaseViewDto databaseView, String columnId);

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
