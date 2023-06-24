//package declaration
package ch.nolix.system.sqldatabaserawschema.schemawriter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SqlCollector;
import ch.nolix.core.sql.SqlConnection;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class SchemaWriter implements ISchemaWriter {
	
	//static method
	public static SchemaWriter forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaAdapter(
		final String databaseName,
		final SqlConnectionPool sqlConnectionPool,
		final ISchemaAdapter schemaAdapter
	) {
		return
		new SchemaWriter(
			databaseName,
			sqlConnectionPool.borrowSqlConnection(),
			schemaAdapter
		);
	}
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//attribute
	private int saveCount;
	
	//attribute
	private final SystemDataWriter systemDataWriter;
	
	//attribute
	private final InternalSchemaWriter internalSchemaWriter;
	
	//attribute
	private final SqlCollector sqlCollector = new SqlCollector();
	
	//attribute
	private final SqlConnection sqlConnection;
	
	//constructor
	public SchemaWriter(
		final String databaseName,
		final SqlConnection sqlConnection,
		final ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaWriter schemaWriter
	) {
		
		GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();
		
		this.sqlConnection = sqlConnection;
		systemDataWriter = new SystemDataWriter(sqlCollector);
		internalSchemaWriter = new InternalSchemaWriter(schemaWriter);		
		
		createCloseDependencyTo(sqlConnection);
		sqlConnection.execute("USE " + databaseName);
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
	public CloseController getOriCloseController() {
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
	public void noteClose() {
		//Does nothing.
	}
	
	//method
	@Override
	public void reset() {
		sqlCollector.clear();
		internalSchemaWriter.reset();
	}
	
	//method
	@Override
	public void saveChangesAndReset() {
		try {
			
			sqlCollector.addSqlStatements(internalSchemaWriter.getSqlStatements());
			sqlCollector.executeUsingConnection(sqlConnection);
			
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
	public void setSchemaTimestamp(final ITime schemaTimestamp) {
		systemDataWriter.setSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public void setTableName(final String tableName, final String newTableName) {
		systemDataWriter.setTableName(tableName, newTableName);
		internalSchemaWriter.setTableName(tableName, newTableName);
	}
}
