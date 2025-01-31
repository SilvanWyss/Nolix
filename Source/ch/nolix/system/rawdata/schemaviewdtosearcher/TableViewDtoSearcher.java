package ch.nolix.system.rawdata.schemaviewdtosearcher;

import ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public final class TableViewDtoSearcher implements ITableViewDtoSearcher {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnSchemaViewDto getColumnViewByColumnId(final TableSchemaViewDto tableSchemaViewDto, final String columnId) {
    return tableSchemaViewDto.columnSchemaViews().getStoredFirst(c -> c.id().equals(columnId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnSchemaViewDto getColumnViewByColumnName(final TableSchemaViewDto tableSchemaViewDto, final String columnName) {
    return tableSchemaViewDto.columnSchemaViews().getStoredFirst(c -> c.name().equals(columnName));
  }
}
