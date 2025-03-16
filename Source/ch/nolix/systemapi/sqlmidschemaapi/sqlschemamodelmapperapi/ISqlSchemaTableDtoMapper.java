package ch.nolix.systemapi.sqlmidschemaapi.sqlschemamodelmapperapi;

import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;

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
