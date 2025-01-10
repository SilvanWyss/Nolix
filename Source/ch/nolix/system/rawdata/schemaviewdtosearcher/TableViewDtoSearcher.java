package ch.nolix.system.rawdata.schemaviewdtosearcher;

import ch.nolix.systemapi.rawdataapi.schemaviewdto.ColumnViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public final class TableViewDtoSearcher implements ITableViewDtoSearcher {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnViewDto getColumnViewByColumnId(final TableViewDto tableViewDto, final String columnId) {
    return tableViewDto.columnViews().getStoredFirst(c -> c.id().equals(columnId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnViewDto getColumnViewByColumnName(final TableViewDto tableViewDto, final String columnName) {
    return tableViewDto.columnViews().getStoredFirst(c -> c.name().equals(columnName));
  }
}
