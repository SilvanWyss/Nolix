//package declaration
package ch.nolix.systemapi.sqlrawschemaapi.tabletableapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;

//interface
public interface ITableFlatDtoMapper {

  //method declaration
  IFlatTableDto createTableDto(IContainer<String> tableSystemTableRecord);
}
