//package declaration
package ch.nolix.system.databaseschema.sqlschemaadapter;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.databaseschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.databaseschemaapi.intermediateschemaapi.IIntermediateSchemaReader;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IColumnDTO;

//class
final class SQLIntermediateSchemaReader implements IIntermediateSchemaReader {
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//constructor
	public SQLIntermediateSchemaReader(final SQLConnection pSQLConnection) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
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
