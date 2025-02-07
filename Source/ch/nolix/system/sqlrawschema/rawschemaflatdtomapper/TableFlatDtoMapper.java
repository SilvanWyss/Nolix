package ch.nolix.system.sqlrawschema.rawschemaflatdtomapper;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.rawschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableTableFieldIndexCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.rawschemaflatdtomapperapi.ITableFlatDtoMapper;

public final class TableFlatDtoMapper implements ITableFlatDtoMapper {

  @Override
  public FlatTableDto mapTableTableSqlRecordToFlatTableDto(final ISqlRecord tableTableSqlRecord) {

    final var tableId = tableTableSqlRecord.getStoredAt1BasedIndex(TableTableFieldIndexCatalog.ID_INDEX);
    final var tableName = tableTableSqlRecord.getStoredAt1BasedIndex(TableTableFieldIndexCatalog.NAME_INDEX);

    return new FlatTableDto(tableId, tableName);
  }
}
