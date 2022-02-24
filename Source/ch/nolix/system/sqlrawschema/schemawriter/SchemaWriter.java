//package declaration
package ch.nolix.system.sqlrawschema.schemawriter;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SQLCollector;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.element.time.base.Time;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
public final class SchemaWriter implements ISchemaWriter {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private int saveCount;
	
	//attribute
	private final SystemDataWriter systemDataWriter;
	
	//attribute
	private final InternalSchemaWriter internalSchemaWriter;
	
	//attribute
	private final SQLCollector mSQLCollector = new SQLCollector();
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//constructor
	public SchemaWriter(
		final String databaseName,
		final SQLConnection pSQLConnection,
		final ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaWriter schemaWriter,
		final ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDTO pSQLSaveStampColumnDTO
	) {
		
		Validator.assertThat(pSQLConnection).thatIsNamed(SQLConnection.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		systemDataWriter = new SystemDataWriter(mSQLCollector);
		internalSchemaWriter = new InternalSchemaWriter(schemaWriter, pSQLSaveStampColumnDTO);		
		
		createCloseDependencyTo(pSQLConnection);
		mSQLConnection.execute("USE " + databaseName);
	}
	
	//method
	@Override
	public void addColumn(final String tableName, final IColumnDTO column) {
		systemDataWriter.addColumn(tableName, column);
		internalSchemaWriter.addColumn(tableName, column);
	}
	
	//method
	@Override
	public void addTable(final ITableDTO table) {
		systemDataWriter.addTable(table);
		internalSchemaWriter.addTable(table);
	}
	
	//method
	@Override
	public void deleteColumn(final String tableName, final String columnName) {
		systemDataWriter.deleteColumn(tableName, columnName);
		internalSchemaWriter.deleteColumn(tableName, columnName);
	}
	
	//method
	@Override
	public void deleteTable(final String tableName) {
		systemDataWriter.deleteTable(tableName);
		internalSchemaWriter.deleteTable(tableName);
	}
	
	//method
	@Override
	public CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public int getSaveCount() {
		return saveCount;
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return (systemDataWriter.hasChanges() || internalSchemaWriter.hasChanges());
	}
	
	//method
	@Override
	public void noteClose() {}
	
	//method
	@Override
	public void reset() {
		mSQLCollector.clear();
		internalSchemaWriter.reset();
	}
	
	//method
	@Override
	public void saveChangesAndReset() {
		try {
			
			mSQLCollector.addSQLStatements(internalSchemaWriter.getSQLStatements());
			mSQLCollector.executeUsingConnection(mSQLConnection);
			
			saveCount++;
		} finally {
			reset();
		}
	}
	
	//method
	@Override
	public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
		systemDataWriter.setColumnName(tableName, columnName, newColumnName);
		internalSchemaWriter.setColumnName(tableName, columnName, newColumnName);
	}
	
	//method
	@Override
	public void setColumnParametrizedPropertyType(
		final String columnId,
		final IParametrizedPropertyTypeDTO parametrizedPropertyType
	) {
		systemDataWriter.setColumnParametrizedPropertyType(columnId, parametrizedPropertyType);
	}
	
	//method
	@Override
	public void setSchemaTimestamp(final Time schemaTimestamp) {
		systemDataWriter.setSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public void setTableName(final String tableName, final String newTableName) {
		systemDataWriter.setTableName(tableName, newTableName);
		internalSchemaWriter.setTableName(tableName, newTableName);
	}
}
