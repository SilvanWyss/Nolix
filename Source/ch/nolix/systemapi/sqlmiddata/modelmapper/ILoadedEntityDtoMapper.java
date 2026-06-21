/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmiddata.modelmapper;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.middata.model.FieldDto;
import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;

/**
 * @author Silvan Wyss
 */
public interface ILoadedEntityDtoMapper {
  /**
   * @param sqlRecord
   * @param columnViews
   * @return new {@link FieldDto}s from the given sqlRecord.
   */
  ExtendedIterable<FieldDto> mapSqlRecordToContentFieldDtos(
    ISqlRecord sqlRecord,
    ExtendedIterable<ColumnInfoDto> columnViews);

  /**
   * @param sqlRecord
   * @param tableView
   * @return a new {@link EntityLoadingDto} from the given sqlRecord.
   */
  EntityLoadingDto mapSqlRecordToEntityLoadingDto(ISqlRecord sqlRecord, TableInfoDto tableView);

}
