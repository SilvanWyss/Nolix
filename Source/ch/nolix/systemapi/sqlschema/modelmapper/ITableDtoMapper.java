package ch.nolix.systemapi.sqlschema.modelmapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 * @version 2025-05-11
 */
public interface ITableDtoMapper {
  /**
   * @param tableName
   * @param sqlRecordsWithNameAndDataType
   * @return a new {@link TableDto} from the given sqlRecordsWithNameAndDataType.
   * @throws RuntimeException if the given sqlRecordsWithNameAndDataType is null.
   * @throws RuntimeException if the given one of the given
   *                          sqlRecordsWithNameAndDataType is null.
   */
  TableDto mapSqlRecordsWithNameAndDataTypeToTableDto(
    String tableName,
    IContainer<ISqlRecord> sqlRecordsWithNameAndDataType);

  /**
   * @param sqlRecordsWithTableNameAndNameAndDataType
   * @return new {@link TableDto}s from the given
   *         sqlRecordsWithTableNameAndNameAndDataType.
   * @throws RuntimeException if the given
   *                          sqlRecordsWithTableNameAndNameAndDataType is null.
   * @throws RuntimeException if the given one of the given
   *                          sqlRecordsWithTableNameAndNameAndDataType is null.
   */
  IContainer<TableDto> mapSqlRecordsWithTableNameAndNameAndDataTypeToTableDtos(
    IContainer<ISqlRecord> sqlRecordsWithTableNameAndNameAndDataType);
}
