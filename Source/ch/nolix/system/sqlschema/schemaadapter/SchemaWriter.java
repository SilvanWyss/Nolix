//package declaration
package ch.nolix.system.sqlschema.schemaadapter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.sql.SQLCollector;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.sqlschemaapi.schemalanguageapi.ISchemaStatementCreator;

//class
public class SchemaWriter implements ISchemaWriter {
	
	//attribute
	private final SQLCollector mSQLCollector = new SQLCollector();
	
	//attribute
	private final SQLConnection mSQLConnection;
	
	//attribute
	private final ISchemaStatementCreator schemaStatementCreator;
	
	//constructor
	public SchemaWriter(
		final SQLConnection pSQLConnection,
		final ISchemaStatementCreator schemaStatementCreator
	) {
		
		Validator.assertThat(schemaStatementCreator).thatIsNamed(ISchemaStatementCreator.class).isNotNull();
		
		mSQLConnection = pSQLConnection;
		this.schemaStatementCreator = schemaStatementCreator;
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
	public void saveChanges() {
		mSQLCollector.executeUsingConnection(mSQLConnection);
	}
}
