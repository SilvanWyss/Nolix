//package declaration
package ch.nolix.system.databaseschema.sqlschemaadapter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.databaseschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.databaseschemaapi.intermediateschemaapi.IIntermediateSchemaReader;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IColumnDTO;

//class
final class SQLIntermediateSchemaReader implements IIntermediateSchemaReader {
	
	//method
	@Override
	public boolean columnIsEmpty(final String tableName, final String columnHeader) {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	public LinkedList<IColumnDTO> loadColumns(final String tableName) {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public LinkedList<IFlatTableDTO> loadFlatTables() {
		//TODO: Implement.
		return null;
	}
	
	//method
	@Override
	public Time loadSchemaTimestamp() {
		//TODO: Implement.
		return null;
	}
	
	//method
	private boolean isContentDataTable(final ch.nolix.techapi.sqlschemaapi.flatschemadtoapi.IFlatTableDTO flatTableDTO) {
		return flatTableDTO.getName().startsWith(TableType.CONTENT_DATA.getPrefix());
	}
}
