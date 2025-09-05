package ch.nolix.systemapi.midschemaview.modelmapper;

import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-10
 */
public interface IColumnViewDtoMapper {
  /**
   * @param columnDto
   * @param oneBasedColumnOrdinalIndex
   * @return a new {@link ColumnViewDto} from the given columnDto and the given
   *         oneBasedColumnOrdinalIndex.
   * @throws RuntimeException if the given columnDto is null.
   */
  ColumnViewDto mapMidSchemaColumnDtoToColumnViewDto(ColumnDto columnDto, int oneBasedColumnOrdinalIndex);
}
