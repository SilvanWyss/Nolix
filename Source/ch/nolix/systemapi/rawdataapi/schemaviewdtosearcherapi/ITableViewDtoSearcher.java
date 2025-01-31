package ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi;

import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public interface ITableViewDtoSearcher {

  /**
   * @param tableSchemaViewDto
   * @param columnId
   * @return the column view of the column with the given columnId from the given
   *         tableViewDto.
   * @throws RuntimeException if the given tableViewDto does not contain a column
   *                          view of a column with the given columnId.
   */
  ColumnSchemaViewDto getColumnViewByColumnId(TableSchemaViewDto tableSchemaViewDto, String columnId);

  /**
   * @param tableSchemaViewDto
   * @param columnName
   * @return the column view of the column with the given columnName from the
   *         given tableViewDto.
   * @throws RuntimeException if the given tableViewDto does not contain a column
   *                          view of a column with the given columnName.
   */
  ColumnSchemaViewDto getColumnViewByColumnName(TableSchemaViewDto tableSchemaViewDto, String columnName);
}
