package ch.nolix.systemapi.middataapi.schemaviewmapperapi;

import ch.nolix.systemapi.middataapi.schemaviewapi.ColumnViewDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public interface IColumnViewDtoMapper {

  /**
   * @param columnDto
   * @param oneBasedColumnOrdinalIndex
   * @return a new {@link ColumnViewDto} from the given columnDto and the
   *         given oneBasedColumnOrdinalIndex.
   * @throws RuntimeException if the given columnDto is null.
   */
  ColumnViewDto mapMidSchemaColumnDtoToColumnViewDto(ColumnDto columnDto, int oneBasedColumnOrdinalIndex);
}
