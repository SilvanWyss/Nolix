package ch.nolix.system.midschemaview.modelsearcher;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.midschemaviewapi.modelapi.ColumnViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.DatabaseViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelsearcherapi.IDatabaseViewSearcher;
import ch.nolix.systemapi.midschemaviewapi.modelsearcherapi.IDatabaseViewSearcherForDatabaseView;

/**
 * @author Silvan Wyss
 * @version 2025-06-27
 */
public final class DatabaseViewSearcherForDatabaseView implements IDatabaseViewSearcherForDatabaseView {

  private static final IDatabaseViewSearcher DATABASE_VIEW_SEARCHER = new DatabaseViewSearcher();

  private final DatabaseViewDto databaseView;

  /**
   * Creates a new {@link DatabaseViewSearcherForDatabaseView} for the given
   * databaseView.
   * 
   * @param databaseView
   * @throws RuntimeException if the given databaseView is null.
   */
  private DatabaseViewSearcherForDatabaseView(final DatabaseViewDto databaseView) {

    Validator.assertThat(databaseView).thatIsNamed("database view").isNotNull();

    this.databaseView = databaseView;
  }

  /**
   * @param databaseView
   * @return a new {@link DatabaseViewSearcherForDatabaseView} for the given
   *         databaseView.
   * @throws RuntimeException if the given databaseView is null.
   */
  public static DatabaseViewSearcherForDatabaseView forDatabaseView(final DatabaseViewDto databaseView) {
    return new DatabaseViewSearcherForDatabaseView(databaseView);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnViewDto getColumnViewByTableNameAndColumnId(final String tableName, final String columnId) {
    return DATABASE_VIEW_SEARCHER.getColumnViewByTableNameAndColumnId(databaseView, tableName, columnId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnViewDto getColumnViewByTableNameAndColumnName(final String tableName, final String columnName) {
    return DATABASE_VIEW_SEARCHER.getColumnViewByTableNameAndColumnName(databaseView, tableName, columnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TableViewDto getTableViewByTableId(final String tableId) {
    return DATABASE_VIEW_SEARCHER.getTableViewByTableId(databaseView, tableId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TableViewDto getTableViewByTableName(final String tableName) {
    return DATABASE_VIEW_SEARCHER.getTableViewByTableName(databaseView, tableName);
  }
}
