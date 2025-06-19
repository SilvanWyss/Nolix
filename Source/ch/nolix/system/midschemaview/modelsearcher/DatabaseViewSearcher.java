package ch.nolix.system.midschemaview.modelsearcher;

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
  public TableViewDto getTableViewByTableName(final DatabaseViewDto databaseView, final String tableName) {

    final var tableViews = databaseView.tableSchemaViews();

    return tableViews.getStoredFirst(t -> t.name().equals(tableName));
  }
}
