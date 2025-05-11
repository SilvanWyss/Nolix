package ch.nolix.system.middata.midschemaviewsearcher;

import ch.nolix.systemapi.middataapi.midschemaview.ColumnViewDto;
import ch.nolix.systemapi.middataapi.midschemaview.TableViewDto;
import ch.nolix.systemapi.middataapi.midschemaviewsearcherapi.ITableViewDtoSearcher;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public final class TableViewDtoSearcher implements ITableViewDtoSearcher {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnViewDto getColumnViewByColumnId(
    final TableViewDto tableViewDto,
    final String columnId) {
    return tableViewDto.columnSchemaViews().getStoredFirst(c -> c.id().equals(columnId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnViewDto getColumnViewByColumnName(
    final TableViewDto tableViewDto,
    final String columnName) {
    return tableViewDto.columnSchemaViews().getStoredFirst(c -> c.name().equals(columnName));
  }
}
