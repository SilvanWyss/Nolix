/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.midschemainfo.modelsearcher;

import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.DatabaseInfoDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.midschemainfo.modelsearcher.IDatabaseInfoSearcher;

/**
 * @author Silvan Wyss
 */
public final class DatabaseInfoSearcher implements IDatabaseInfoSearcher {
  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnInfoDto getColumnViewByTableNameAndColumnId(
    final DatabaseInfoDto databaseView,
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
  public ColumnInfoDto getColumnViewByTableNameAndColumnName(
    final DatabaseInfoDto databaseView,
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
  public TableInfoDto getTableViewByTableId(final DatabaseInfoDto databaseView, final String tableId) {
    final var tableViews = databaseView.tableViews();

    return tableViews.getStoredFirst(t -> t.id().equals(tableId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TableInfoDto getTableViewByTableName(final DatabaseInfoDto databaseView, final String tableName) {
    final var tableViews = databaseView.tableViews();

    return tableViews.getStoredFirst(t -> t.name().equals(tableName));
  }
}
