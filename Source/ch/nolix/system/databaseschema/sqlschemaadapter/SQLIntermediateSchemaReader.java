//package declaration
package ch.nolix.system.databaseschema.sqlschemaadapter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.databaseschema.flatschemadto.FlatTableDTO;
import ch.nolix.system.databaseschema.schemadto.ColumnDTO;
import ch.nolix.system.sqlschema.schemaadapter.SchemaReader;
import ch.nolix.techapi.databaseschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.databaseschemaapi.intermediateschemaapi.IIntermediateSchemaReader;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IColumnDTO;

//class
final class SQLIntermediateSchemaReader implements IIntermediateSchemaReader {
	
	private final SchemaReader schemaReader;
	private final SQLConnection mSQLConnection;
	
	//method
	@Override
	public boolean columnIsEmpty(final String tableName, final String columnHeader) {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	public LinkedList<IColumnDTO> loadColumns(final String tableName) {
		return
		SQLConnection
		.getRows("SELECT Name, DataType FROM " + SystemDataTable.COLUMNS.getName())
		.to(r -> new ColumnDTO(r., parametrizedPropertyTypeDTO))
	}
	
	//method
	@Override
	public LinkedList<IFlatTableDTO> loadFlatTables() {
		return
		schemaReader
		.loadFlatTables()
		.getRefSelected(this::isContentDataTable)
		.to(t -> new FlatTableDTO(t.getName().substring(1)));
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
