//package declaration
package ch.nolix.system.sqlbasicschema.schemaadapter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SQLCollector;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.core.sql.SQLConnectionPool;
import ch.nolix.systemapi.sqlbasicschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemalanguageapi.ISchemaStatementCreator;

//class
public final class SchemaWriter implements ISchemaWriter {
	
	//static method
	public static SchemaWriter forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaStatementCreator(
		final String databaseName,
		final SQLConnectionPool pSQLConnectionPool,
		final ISchemaStatementCreator schemaStatementCreator
	) {
		return new SchemaWriter(databaseName, pSQLConnectionPool.borrowSQLConnection(), schemaStatementCreator);
	}
	
	//attribute
	private int saveCount;
	
	//attribute
	private final SQLCollector mSQLCollector = new SQLCollector();
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//attribute
	private final ISchemaStatementCreator schemaStatementCreator;
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//constructor
	private SchemaWriter(
		final String databaseName,
		final SQLConnection pSQLConnection,
		final ISchemaStatementCreator schemaStatementCreator
	) {
		
		GlobalValidator.assertThat(schemaStatementCreator).thatIsNamed(ISchemaStatementCreator.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		this.schemaStatementCreator = schemaStatementCreator;
		
		getRefCloseController().createCloseDependencyTo(mSQLConnection);
		mSQLCollector.addSQLStatement("USE " + databaseName);
	}
	
	//method
	@Override
	public void addColumn(final String tableName, final IColumnDTO column) {
		mSQLCollector.addSQLStatement(schemaStatementCreator.createStatementToAddColumn(tableName, column));
	}
	
	//method
	@Override
	public void addTable(final ITableDTO table) {
		mSQLCollector.addSQLStatement(schemaStatementCreator.createStatementToAddTable(table));
	}
	
	//method
	@Override
	public void deleteColumn(final String tableName, final String columnName) {
		mSQLCollector.addSQLStatement(schemaStatementCreator.createStatementToDeleteColumn(tableName, columnName));
	}
	
	//method
	@Override
	public void deleteTable(final String tableName) {
		mSQLCollector.addSQLStatement(schemaStatementCreator.createStatementToDeleteTable(tableName));
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
	public IContainer<String> getSQLStatements() {
		return mSQLCollector.getSQLStatements();
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return mSQLCollector.containsAny();
	}
	
	//method
	@Override
	public void noteClose() {
		//Does nothing.
	}
	
	//method
	@Override
	public void renameColumn(final String tableName, final String columnName, final String newColumnName) {
		mSQLCollector.addSQLStatement(
			schemaStatementCreator.createStatementToRenameColumn(tableName, columnName, newColumnName)
		);
	}
	
	//method
	@Override
	public void renameTable(final String tableName, final String newTableName) {
		mSQLCollector.addSQLStatement(schemaStatementCreator.createStatementToRenameTable(tableName, newTableName));
	}
	
	//method
	@Override
	public void reset() {
		mSQLCollector.clear();
	}
	
	//method
	@Override
	public void saveChangesAndReset() {
		try {
			mSQLCollector.executeUsingConnection(mSQLConnection);
			saveCount++;
		} finally {
			reset();
		}
	}
}
