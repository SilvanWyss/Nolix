//package declaration
package ch.nolix.system.sqlschema.schemaadapter;

//own imports
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.common.sql.SQLExecutor;
import ch.nolix.techapi.sqlschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.techapi.sqlschemaapi.schemalanguageapi.ISchemaStatementCreator;

//class
public abstract class SchemaWriter implements ISchemaWriter {
	
	//attributes
	private final SQLExecutor mSQLExecutor;
	private final ISchemaStatementCreator schemaStatementCreator;
	
	//constructor
	public SchemaWriter(final SQLConnection pSQLConnection, final ISchemaStatementCreator schemaStatementCreator) {
		
		Validator.assertThat(schemaStatementCreator).thatIsNamed(ISchemaStatementCreator.class).isNotNull();
		
		mSQLExecutor = new SQLExecutor(pSQLConnection);
		this.schemaStatementCreator = schemaStatementCreator;
	}
	
	//method
	@Override
	public final void addColumn(final String tableName, final IColumnDTO column) {
		mSQLExecutor.addSQLStatement(schemaStatementCreator.createStatementToAddColumn(tableName, column));
	}
	
	//method
	@Override
	public final void addTable(final ITableDTO table) {
		mSQLExecutor.addSQLStatement(schemaStatementCreator.createStatementToAddTable(table));
	}
	
	//method
	@Override
	public final void deleteColumn(final String tableName, final String columnName) {
		mSQLExecutor.addSQLStatement(schemaStatementCreator.createStatementToDeleteColumn(tableName, columnName));
	}
	
	//method
	@Override
	public final void deleteTable(final String tableName) {
		mSQLExecutor.addSQLStatement(schemaStatementCreator.createStatementToDeleteTable(tableName));
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return mSQLExecutor.getStatements().containsAny();
	}
	
	//method
	@Override
	public final void renameColumn(final String tableName, final String columnName, final String newColumnName) {
		mSQLExecutor.addSQLStatement(
			schemaStatementCreator.createStatementToRenameColumn(tableName, columnName, newColumnName)
		);
	}
	
	//method
	@Override
	public final void renameTable(final String tableName, final String newTableName) {
		mSQLExecutor.addSQLStatement(schemaStatementCreator.createStatementToRenameTable(tableName, newTableName));
	}
	
	//method
	@Override
	public final void saveChanges() {
		mSQLExecutor.execute();
	}
}
