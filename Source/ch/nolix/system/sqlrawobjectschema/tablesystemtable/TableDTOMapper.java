//package declaration
package ch.nolix.system.sqlrawobjectschema.tablesystemtable;

//Java imports
import java.util.List;

import ch.nolix.system.objectschema.flatschemadto.FlatTableDTO;
import ch.nolix.techapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;

//class
public final class TableDTOMapper {
	
	//method
	public IFlatTableDTO createTableDTO(final List<String> tableSystemTableRecord) {
		return new FlatTableDTO(tableSystemTableRecord.get(0));
	}
}
