package ch.nolix.systemapi.sqlmidschemaapi.modelmapperapi;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public interface IColumnDtoMapper {

  /**
   * @param columnTableSqlRecord
   * @return a new {@link ColumnDto} from the given columnTableSqlRecord
   * @throws RuntimeException if the given columnTableSqlRecord is null.
   */
  ColumnDto mapColumnTableSqlRecordToColumnDto(ISqlRecord columnTableSqlRecord);

  /**
   * @param joinedColumnDto
   * @return a new {@link ColumnDto} from the given joinedColumnDto.
   * @throws RuntimeException if the given joinedColumnDto is null.
   */
  ColumnDto mapJoinedColumnSqlRecordToColumnDto(ISqlRecord joinedColumnDto);
}
