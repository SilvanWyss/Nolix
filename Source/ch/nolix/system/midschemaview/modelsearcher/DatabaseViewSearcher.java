package ch.nolix.system.midschemaview.modelsearcher;

import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;
import ch.nolix.systemapi.midschemaview.model.DatabaseViewDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;
import ch.nolix.systemapi.midschemaview.modelsearcher.IDatabaseViewSearcher;

/**
 * @author Silvan Wyss
 * @version 2025-06-19
 */
public final class DatabaseViewSearcher implements IDatabaseViewSearcher {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnViewDto getColumnViewByTableNameAndColumnId(
    final DatabaseViewDto databaseView,
    final String tableName,
    final String columnId) {

    final var tableView = getTableViewByTableName(databaseView, tableName);
    final var columnViews = tableView.columnViews();

    return columnViews.getStoredFirst(c -> c.id().equals(columnId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnViewDto getColumnViewByTableNameAndColumnName(
    final DatabaseViewDto databaseView,
    final String tableName,
    final String columnName) {

    final var tableView = getTableViewByTableName(databaseView, tableName);
    final var columnViews = tableView.columnViews();

    return columnViews.getStoredFirst(c -> c.name().equals(columnName));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TableViewDto getTableViewByTableId(final DatabaseViewDto databaseView, final String tableId) {

    final var tableViews = databaseView.tableViews();

    return tableViews.getStoredFirst(t -> t.id().equals(tableId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TableViewDto getTableViewByTableName(final DatabaseViewDto databaseView, final String tableName) {

    final var tableViews = databaseView.tableViews();

    return tableViews.getStoredFirst(t -> t.name().equals(tableName));
  }
}
