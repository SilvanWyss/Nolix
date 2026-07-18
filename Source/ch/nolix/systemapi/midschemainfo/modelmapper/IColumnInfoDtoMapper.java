/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschemainfo.modelmapper;

import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;

/**
 * @author Silvan Wyss
 */
public interface IColumnInfoDtoMapper {
  /**
   * @param columnDto
   * @param oneBasedColumnOrdinalIndex
   * @return a new {@link ColumnInfoDto} from the given columnDto and the given
   *         oneBasedColumnOrdinalIndex
   * @throws RuntimeException if the given columnDto is null.
   */
  ColumnInfoDto mapMidSchemaColumnDtoToColumnViewDto(ColumnDto columnDto, int oneBasedColumnOrdinalIndex);
}
