package ch.nolix.system.sqlmidschema.flatmodelmapper;

import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.systemapi.midschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.TableTableFieldIndexCatalog;
import ch.nolix.systemapi.sqlmidschemaapi.flatmodelmapperapi.ITableFlatDtoMapper;

public final class TableFlatDtoMapper implements ITableFlatDtoMapper {

  @Override
  public FlatTableDto mapTableTableSqlRecordToFlatTableDto(final ISqlRecord tableTableSqlRecord) {

    final var tableId = tableTableSqlRecord.getStoredAtOneBasedIndex(TableTableFieldIndexCatalog.ID_INDEX);
    final var tableName = tableTableSqlRecord.getStoredAtOneBasedIndex(TableTableFieldIndexCatalog.NAME_INDEX);

    return new FlatTableDto(tableId, tableName);
  }
}
