package ch.nolix.systemapi.rawdataapi.schemaviewmapperapi;

import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnViewDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public interface IColumnViewDtoMapper {

  /**
   * @param columnDto
   * @param oneBasedColumnOrdinalIndex
   * @return a new {@link ColumnViewDto} from the given {@link ColumnDto} and the
   *         given oneBasedColumnOrdinalIndex.
   * @throws RuntimeException if the given columnDto is null.
   */
  ColumnViewDto mapColumnDtoToColumnViewDto(ColumnDto columnDto, int oneBasedColumnOrdinalIndex);
}
