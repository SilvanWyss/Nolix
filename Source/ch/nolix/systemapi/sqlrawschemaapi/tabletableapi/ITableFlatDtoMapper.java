package ch.nolix.systemapi.sqlrawschemaapi.tabletableapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;

public interface ITableFlatDtoMapper {

  IFlatTableDto createTableDto(IContainer<String> tableSystemTableRecord);
}
