package ch.nolix.systemapi.rawdataapi.schemaviewmapperapi;

import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-24
 */
public interface ITableSchemaViewDtoMapper {

  /**
   * @param tableDto
   * @return a new {@link TableSchemaViewDto} from the given tableDto.
   * @throws RuntimeException if the given tableDto is null.
   */
  TableSchemaViewDto mapTableDtoToTableSchemaViewDto(TableDto tableDto);
}
