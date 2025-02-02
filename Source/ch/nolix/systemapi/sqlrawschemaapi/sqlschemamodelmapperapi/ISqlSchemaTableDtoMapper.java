package ch.nolix.systemapi.sqlrawschemaapi.sqlschemamodelmapperapi;

import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;

/**
 * @author Silvan Wyss
 * @version 2025-02-02
 */
public interface ISqlSchemaTableDtoMapper {

  /**
   * @param tableDto
   * @return a new {@link ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto} from
   *         the given tableDto.
   * @throws RuntimeException if the given tableDto is null.
   */
  ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto mapTableDtoSqlSchemaTableDto(TableDto tableDto);
}
