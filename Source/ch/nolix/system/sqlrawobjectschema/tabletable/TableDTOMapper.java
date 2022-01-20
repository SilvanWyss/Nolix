//package declaration
package ch.nolix.system.sqlrawobjectschema.tabletable;

//Java imports
import java.util.List;

//own imports
import ch.nolix.system.objectschema.flatschemadto.FlatTableDTO;
import ch.nolix.techapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;

//class
public final class TableDTOMapper {
	
	//method
	public IFlatTableDTO createTableDTO(final List<String> tableSystemTableRecord) {
		return new FlatTableDTO(tableSystemTableRecord.get(0), tableSystemTableRecord.get(1));
	}
}
