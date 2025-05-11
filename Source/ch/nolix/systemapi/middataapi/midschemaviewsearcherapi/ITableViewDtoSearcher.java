package ch.nolix.systemapi.middataapi.midschemaviewsearcherapi;

import ch.nolix.systemapi.middataapi.midschemaview.ColumnViewDto;
import ch.nolix.systemapi.middataapi.midschemaview.TableViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public interface ITableViewDtoSearcher {

  /**
   * @param tableViewDto
   * @param columnId
   * @return the column view of the column with the given columnId from the given
   *         tableViewDto.
   * @throws RuntimeException if the given tableViewDto does not contain a column
   *                          view of a column with the given columnId.
   */
  ColumnViewDto getColumnViewByColumnId(TableViewDto tableViewDto, String columnId);

  /**
   * @param tableViewDto
   * @param columnName
   * @return the column view of the column with the given columnName from the
   *         given tableViewDto.
   * @throws RuntimeException if the given tableViewDto does not contain a column
   *                          view of a column with the given columnName.
   */
  ColumnViewDto getColumnViewByColumnName(TableViewDto tableViewDto, String columnName);
}
