//package declaration
package ch.nolix.system.intermediateschema;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.databaseschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.IIntermediateSchemaReader;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
final class IntermediateSchemaReader implements IIntermediateSchemaReader {
	
	//attribute
	private final SQLConnection mSQLConnection;
	private final ISchemaAdapter schemaAdapter;
	
	//constructor
	public IntermediateSchemaReader(final SQLConnection pSQLConnection, final ISchemaAdapter schemaAdapter) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		Validator.assertThat(schemaAdapter).thatIsNamed(ISchemaAdapter.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		this.schemaAdapter = schemaAdapter;
	}
	
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
}
