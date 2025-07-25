package ch.nolix.systemapi.midschemaview.modelsearcher;

import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-06-27
 */
public interface IDatabaseViewSearcherForDatabaseView {

  /**
   * @param tableName
   * @param columnId
   * @return the {@link ColumnViewDto} with the given columnId from the table with
   *         the given tableName from the database view of the current
   *         {@link IDatabaseViewSearcherForDatabaseView}.
   * @throws RuntimeException if the database view of the current
   *                          {@link IDatabaseViewSearcherForDatabaseView} does
   *                          not contain a table with the given tableName, that
   *                          contains a {@link ColumnViewDto} with the given
   *                          columnId.
   */
  ColumnViewDto getColumnViewByTableNameAndColumnId(String tableName, String columnId);

  /**
   * @param tableName
   * @param columnName
   * @return the {@link ColumnViewDto} with the given columnName from the table
   *         with the given tableName from the database view of the current
   *         {@link IDatabaseViewSearcherForDatabaseView}.
   * @throws RuntimeException if the database view of the current
   *                          {@link IDatabaseViewSearcherForDatabaseView} does
   *                          not contain a table with the given tableName, that
   *                          contains a {@link ColumnViewDto} with the given
   *                          columnName.
   */
  ColumnViewDto getColumnViewByTableNameAndColumnName(String tableName, String columnName);

  /**
   * @param tableId
   * @return the {@link TableViewDto} with the given tableId from database view of
   *         the current {@link IDatabaseViewSearcherForDatabaseView}.
   * @throws RuntimeException if the database view of the current
   *                          {@link IDatabaseViewSearcherForDatabaseView} does
   *                          not contain a {@link TableViewDto} with the given
   *                          tableId.
   */
  TableViewDto getTableViewByTableId(String tableId);

  /**
   * @param tableName
   * @return the {@link TableViewDto} with the given tableName from the database
   *         view of the current {@link IDatabaseViewSearcherForDatabaseView}.
   * @throws RuntimeException if the database view of the current
   *                          {@link IDatabaseViewSearcherForDatabaseView} does
   *                          not contain a {@link TableViewDto} with the given
   *                          tableName.
   */
  TableViewDto getTableViewByTableName(String tableName);
}
