//package declaration
package ch.nolix.system.sqldatabaserawschema.tabletable;

//Java imports
import java.util.List;

//own imports
import ch.nolix.system.objectschema.flatschemadto.FlatTableDto;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;

//class
public final class TableDtoMapper {

  // method
  public IFlatTableDto createTableDto(final List<String> tableSystemTableRecord) {
    return new FlatTableDto(tableSystemTableRecord.get(0), tableSystemTableRecord.get(1));
  }
}
