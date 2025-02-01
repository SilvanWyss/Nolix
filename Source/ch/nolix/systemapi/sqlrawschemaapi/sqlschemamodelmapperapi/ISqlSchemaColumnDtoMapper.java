package ch.nolix.systemapi.sqlrawschemaapi.sqlschemamodelmapperapi;

import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-01
 */
public interface ISqlSchemaColumnDtoMapper {

  /**
   * @param columnDto
   * @return a new {@link ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto} from
   *         the given columnDto.
   * @throws RuntimeException if the given columnDto is null.
   */
  ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto mapColumnDtoToSqlSchemaColumnDto(ColumnDto columnDto);
}
