package ch.nolix.systemapi.rawdataapi.schemaviewmapperapi;

import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableViewDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;

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
  TableViewDto mapTableDtoToTableViewDto(TableDto tableDto);
}
