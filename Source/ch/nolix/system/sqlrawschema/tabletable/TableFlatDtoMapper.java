//package declaration
package ch.nolix.system.sqlrawschema.tabletable;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.flatschemadto.FlatTableDto;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;

//class
public final class TableFlatDtoMapper {

  //method
  public IFlatTableDto createTableDto(final IContainer<String> tableSystemTableRecord) {
    return new FlatTableDto(
      tableSystemTableRecord.getStoredAt1BasedIndex(1),
      tableSystemTableRecord.getStoredAt1BasedIndex(2));
  }
}
