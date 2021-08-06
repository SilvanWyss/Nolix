//package declaration
package ch.nolix.system.databaseschema.sqlschemaadapter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.common.sql.SQLExecutor;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.databaseschemaapi.intermediateschemaapi.IIntermediateSchemaWriter;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
final class SQLIntermediateSchemaWriter implements IIntermediateSchemaWriter {
	
	//attributes
	private final SQLExecutor mSQLExecutor;
	private final ISchemaAdapter schemaAdapter;
	
	//constructor
	public SQLIntermediateSchemaWriter(final SQLConnection pSQLConnection, final ISchemaAdapter schemaAdapter) {
		
		Validator.assertThat(schemaAdapter).thatIsNamed(ISchemaAdapter.class).isNotNull();
		
		mSQLExecutor = new SQLExecutor(pSQLConnection);
		this.schemaAdapter = schemaAdapter;
	}
	
	//method
	@Override
	public void addColumn(final String tableName, final IColumnDTO column) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void addTable(final ITableDTO table) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void deleteColumn(final String tableName, final String columnHeader) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void deleteTable(final String tableName) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public boolean hasChanges() {
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	public void setColumnHeader(final String tableName, final String columnHeader, final String newColumnHeader) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void setColumnParametrizedPropertyType(
		final String tableName,
		final String columnHeader,
		final IParametrizedPropertyTypeDTO parametrizedPropertyType
	) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void setSchemaTimestamp(final Time schemaTimestamp) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void setTableName(final String tableName, final String newTableName) {
		//TODO: Implement.
	}
	
	//method
	@Override
	public void saveChanges() {
		//TODO: Implement.
	}
}
