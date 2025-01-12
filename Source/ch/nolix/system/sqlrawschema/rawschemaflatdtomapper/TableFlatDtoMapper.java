package ch.nolix.system.sqlrawschema.rawschemaflatdtomapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.sqlrawschemaapi.rawschemaflatdtomapperapi.ITableFlatDtoMapper;

public final class TableFlatDtoMapper implements ITableFlatDtoMapper {

  @Override
  public FlatTableDto createTableDto(final IContainer<String> tableSystemTableRecord) {
    return new FlatTableDto(
      tableSystemTableRecord.getStoredAt1BasedIndex(1),
      tableSystemTableRecord.getStoredAt1BasedIndex(2));
  }
}
