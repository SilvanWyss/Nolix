/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.modelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.midschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
public interface ITableDtoMapper {
  /**
   * @param joinedColumnSqlRecords
   * @return a new {@link TableDto} from the given joinedColumnSqlRecords
   * @throws RuntimeException if the given joinedColumnSqlRecords is null or one
   *                          of the given joinedColumnSqlRecords is null.
   */
  TableDto mapJoinedColumnSqlRecordsToTableDto(IContainer<ISqlRecord> joinedColumnSqlRecords);

  /**
   * @param joinedColumnSqlRecords
   * @return new {@link TableDto}s from the given joinedColumnSqlRecords
   * @throws RuntimeException if the given joinedColumnSqlRecords is null or one
   *                          of the given joinedColumnSqlRecords is null.
   */
  IContainer<TableDto> mapJoinedColumnSqlRecordsToTableDtos(IContainer<ISqlRecord> joinedColumnSqlRecords);
}
