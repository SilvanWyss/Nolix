package ch.nolix.system.middata.schemaviewdtosearcher;

import ch.nolix.systemapi.middataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;
import ch.nolix.systemapi.middataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.middataapi.schemaviewmodel.TableSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public final class TableViewDtoSearcher implements ITableViewDtoSearcher {

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnSchemaViewDto getColumnViewByColumnId(
    final TableSchemaViewDto tableSchemaViewDto,
    final String columnId) {
    return tableSchemaViewDto.columnSchemaViews().getStoredFirst(c -> c.id().equals(columnId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnSchemaViewDto getColumnViewByColumnName(
    final TableSchemaViewDto tableSchemaViewDto,
    final String columnName) {
    return tableSchemaViewDto.columnSchemaViews().getStoredFirst(c -> c.name().equals(columnName));
  }
}
