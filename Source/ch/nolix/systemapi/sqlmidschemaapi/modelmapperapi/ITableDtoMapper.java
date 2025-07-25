package ch.nolix.systemapi.sqlmidschemaapi.modelmapperapi;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;

/**
 * @author Silvan Wyss
 * @version 2025-05-30
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
