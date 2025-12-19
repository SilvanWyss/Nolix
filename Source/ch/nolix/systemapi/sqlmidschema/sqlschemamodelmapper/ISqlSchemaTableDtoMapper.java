package ch.nolix.systemapi.sqlmidschema.sqlschemamodelmapper;

import ch.nolix.systemapi.midschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public interface ISqlSchemaTableDtoMapper {
  /**
   * @param tableDto
   * @return a new {@link ch.nolix.systemapi.sqlschema.model.TableDto} from the
   *         given tableDto.
   * @throws RuntimeException if the given tableDto is null.
   */
  ch.nolix.systemapi.sqlschema.model.TableDto mapTableDtoSqlSchemaTableDto(TableDto tableDto);
}
