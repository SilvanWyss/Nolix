/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.midschemainfo.modelsearcher;

import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.midschemainfo.modelsearcher.ITableInfoSearcher;

/**
 * @author Silvan Wyss
 */
public final class TableInfoSearcher implements ITableInfoSearcher {
  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnInfoDto getColumnViewByColumnId(
    final TableInfoDto tableInfoDto,
    final String columnId) {
    return tableInfoDto.columnViews().getStoredFirst(c -> c.id().equals(columnId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ColumnInfoDto getColumnViewByColumnName(
    final TableInfoDto tableInfoDto,
    final String columnName) {
    return tableInfoDto.columnViews().getStoredFirst(c -> c.name().equals(columnName));
  }
}
