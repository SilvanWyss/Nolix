package ch.nolix.systemapi.sqlrawschemaapi.rawschemaflatdtomapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawschemaapi.flatmodelapi.FlatTableDto;

public interface ITableFlatDtoMapper {

  FlatTableDto createTableDto(IContainer<String> tableSystemTableRecord);
}
