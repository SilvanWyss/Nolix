package ch.nolix.systemapi.midschemaview.modelmapper;

import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;

/**
 * @author Silvan Wyss
 */
public interface ITableViewDtoMapper {
  /**
   * @param tableDto
   * @return a new {@link TableViewDto} from the given tableDto.
   * @throws RuntimeException if the given tableDto is null.
   */
  TableViewDto mapMidSchemaTableDtoToTableViewDto(TableDto tableDto);
}
