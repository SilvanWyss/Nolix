package ch.nolix.system.midschemaview.modelsearcher;

import ch.nolix.systemapi.midschemaviewapi.modelapi.ColumnViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelsearcherapi.ITableViewSearcher;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public final class TableViewSearcher implements ITableViewSearcher {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnViewDto getColumnViewByColumnId(
    final TableViewDto tableViewDto,
    final String columnId) {
    return tableViewDto.columnViews().getStoredFirst(c -> c.id().equals(columnId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnViewDto getColumnViewByColumnName(
    final TableViewDto tableViewDto,
    final String columnName) {
    return tableViewDto.columnViews().getStoredFirst(c -> c.name().equals(columnName));
  }
}
