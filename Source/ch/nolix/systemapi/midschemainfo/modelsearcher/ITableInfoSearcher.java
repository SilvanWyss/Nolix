/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschemainfo.modelsearcher;

import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface ITableInfoSearcher {
  /**
   * @param tableInfoDto
   * @param columnId
   * @return the column view of the column with the given columnId from the given
   *         tableViewDto
   * @throws RuntimeException if the given tableViewDto does not contain a column
   *                          view of a column with the given columnId.
   */
  ColumnInfoDto getColumnViewByColumnId(TableInfoDto tableInfoDto, String columnId);

  /**
   * @param tableInfoDto
   * @param columnName
   * @return the column view of the column with the given columnName from the
   *         given tableViewDto
   * @throws RuntimeException if the given tableViewDto does not contain a column
   *                          view of a column with the given columnName.
   */
  ColumnInfoDto getColumnViewByColumnName(TableInfoDto tableInfoDto, String columnName);
}
