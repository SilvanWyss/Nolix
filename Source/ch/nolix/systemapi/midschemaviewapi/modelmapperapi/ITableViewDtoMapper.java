package ch.nolix.systemapi.midschemaviewapi.modelmapperapi;

import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-24
 */
public interface ITableViewDtoMapper {

  /**
   * @param tableDto
   * @return a new {@link TableViewDto} from the given tableDto.
   * @throws RuntimeException if the given tableDto is null.
   */
  TableViewDto mapMidSchemaTableDtoToTableViewDto(TableDto tableDto);
}
