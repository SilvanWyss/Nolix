package ch.nolix.systemapi.middataapi.schemaviewmapperapi;

import ch.nolix.systemapi.middataapi.schemaviewapi.TableViewDto;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;

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
