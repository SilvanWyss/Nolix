package ch.nolix.systemapi.sqlrawschemaapi.rawschemaflatdtomapperapi;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.rawschemaapi.flatmodelapi.FlatTableDto;

public interface ITableFlatDtoMapper {

  FlatTableDto mapTableTableSqlRecordToFlatTableDto(ISqlRecord tableTableSqlRecord);
}
