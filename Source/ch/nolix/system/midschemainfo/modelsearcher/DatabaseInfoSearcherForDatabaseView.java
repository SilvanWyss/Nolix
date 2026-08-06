/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.midschemainfo.modelsearcher;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.DatabaseInfoDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.midschemainfo.modelsearcher.IDatabaseInfoSearcherForDatabaseInfo;

/**
 * @author Silvan Wyss
 */
public final class DatabaseInfoSearcherForDatabaseView implements IDatabaseInfoSearcherForDatabaseInfo {
  private static final DatabaseInfoSearcher DATABASE_VIEW_SEARCHER = new DatabaseInfoSearcher();

  private final DatabaseInfoDto databaseView;

  /**
   * Creates a new {@link DatabaseInfoSearcherForDatabaseView} for the given
   * databaseView.
   * 
   * @param databaseView
   * @throws RuntimeException if the given databaseView is null
   */
  private DatabaseInfoSearcherForDatabaseView(final DatabaseInfoDto databaseView) {
    Validator.assertThat(databaseView).thatIsNamed("database view").isNotNull();

    this.databaseView = databaseView;
  }

  /**
   * @param databaseView
   * @return a new {@link DatabaseInfoSearcherForDatabaseView} for the given
   *         databaseView
   * @throws RuntimeException if the given databaseView is null
   */
  public static DatabaseInfoSearcherForDatabaseView forDatabaseView(final DatabaseInfoDto databaseView) {
    return new DatabaseInfoSearcherForDatabaseView(databaseView);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnInfoDto getColumnViewByTableNameAndColumnId(final String tableName, final String columnId) {
    return DATABASE_VIEW_SEARCHER.getColumnViewByTableNameAndColumnId(databaseView, tableName, columnId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnInfoDto getColumnViewByTableNameAndColumnName(final String tableName, final String columnName) {
    return DATABASE_VIEW_SEARCHER.getColumnViewByTableNameAndColumnName(databaseView, tableName, columnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TableInfoDto getTableViewByTableId(final String tableId) {
    return DATABASE_VIEW_SEARCHER.getTableViewByTableId(databaseView, tableId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TableInfoDto getTableViewByTableName(final String tableName) {
    return DATABASE_VIEW_SEARCHER.getTableViewByTableName(databaseView, tableName);
  }
}
