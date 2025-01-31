package ch.nolix.systemapi.rawdataapi.schemaviewmapperapi;

import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public interface IColumnSchemaViewDtoMapper {

  /**
   * @param columnDto
   * @param oneBasedColumnOrdinalIndex
   * @return a new {@link ColumnSchemaViewDto} from the given columnDto and the
   *         given oneBasedColumnOrdinalIndex.
   * @throws RuntimeException if the given columnDto is null.
   */
  ColumnSchemaViewDto mapColumnDtoToColumnSchemaViewDto(ColumnDto columnDto, int oneBasedColumnOrdinalIndex);
}
