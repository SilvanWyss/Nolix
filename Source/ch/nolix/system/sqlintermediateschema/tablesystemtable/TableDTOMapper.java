//package declaration
package ch.nolix.system.sqlintermediateschema.tablesystemtable;

//Java imports
import java.util.List;

import ch.nolix.system.objectschema.flatschemadto.FlatTableDTO;
import ch.nolix.techapi.intermediateschemaapi.flatschemadtoapi.IFlatTableDTO;

//class
public final class TableDTOMapper {
	
	//method
	public IFlatTableDTO createTableDTO(final List<String> tableSystemTableRecord) {
		return new FlatTableDTO(tableSystemTableRecord.get(0));
	}
}
