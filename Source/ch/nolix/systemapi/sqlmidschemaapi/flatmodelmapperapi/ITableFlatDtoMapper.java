package ch.nolix.systemapi.sqlmidschemaapi.flatmodelmapperapi;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.midschemaapi.flatmodelapi.FlatTableDto;

public interface ITableFlatDtoMapper {

  FlatTableDto mapTableTableSqlRecordToFlatTableDto(ISqlRecord tableTableSqlRecord);
}
