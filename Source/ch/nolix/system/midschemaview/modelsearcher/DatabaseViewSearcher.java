package ch.nolix.system.midschemaview.modelsearcher;

import ch.nolix.systemapi.midschemaviewapi.modelapi.ColumnViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.DatabaseViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelsearcherapi.IDatabaseViewSearcher;

/**
 * @author Silvan Wyss
 * @version 2025-06-19
 */
public final class DatabaseViewSearcher implements IDatabaseViewSearcher {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnViewDto getColumnViewByTableNameAndColumnName(
    final DatabaseViewDto databaseView,
    final String tableName,
    final String columnName) {

    final var tableView = getTableViewByTableName(databaseView, tableName);
    final var columnViews = tableView.columnSchemaViews();

    return columnViews.getStoredFirst(c -> c.name().equals(columnName));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TableViewDto getTableViewByTableName(final DatabaseViewDto databaseView, final String tableName) {

    final var tableViews = databaseView.tableSchemaViews();

    return tableViews.getStoredFirst(t -> t.name().equals(tableName));
  }
}
