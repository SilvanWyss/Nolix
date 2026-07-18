/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschemainfo.modelmapper;

import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface ITableInfoDtoMapper {
  /**
   * @param tableDto
   * @return a new {@link TableInfoDto} from the given tableDto
   * @throws RuntimeException if the given tableDto is null
   */
  TableInfoDto mapMidSchemaTableDtoToTableViewDto(TableDto tableDto);
}
