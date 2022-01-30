//package declaration
package ch.nolix.system.sqlschema.schemaadapter;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.sql.SQLConnection;
import ch.nolix.core.sql.SQLExecutor;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.sqlschemaapi.schemalanguageapi.ISchemaStatementCreator;

//class
public class SchemaWriter implements ISchemaWriter {
	
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
	public void addColumn(final String tableName, final IColumnDTO column) {
		mSQLExecutor.addSQLStatement(schemaStatementCreator.createStatementToAddColumn(tableName, column));
	}
	
	//method
	@Override
	public void addTable(final ITableDTO table) {
		mSQLExecutor.addSQLStatement(schemaStatementCreator.createStatementToAddTable(table));
	}
	
	//method
	@Override
	public void deleteColumn(final String tableName, final String columnName) {
		mSQLExecutor.addSQLStatement(schemaStatementCreator.createStatementToDeleteColumn(tableName, columnName));
	}
	
	//method
	@Override
	public void deleteTable(final String tableName) {
		mSQLExecutor.addSQLStatement(schemaStatementCreator.createStatementToDeleteTable(tableName));
	}
	
	//method
	@Override
	public IContainer<String> getSQLStatements() {
		return mSQLExecutor.getSQLStatements();
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return mSQLExecutor.getSQLStatements().containsAny();
	}
	
	//method
	@Override
	public void renameColumn(final String tableName, final String columnName, final String newColumnName) {
		mSQLExecutor.addSQLStatement(
			schemaStatementCreator.createStatementToRenameColumn(tableName, columnName, newColumnName)
		);
	}
	
	//method
	@Override
	public void renameTable(final String tableName, final String newTableName) {
		mSQLExecutor.addSQLStatement(schemaStatementCreator.createStatementToRenameTable(tableName, newTableName));
	}
	
	//method
	@Override
	public void saveChanges() {
		mSQLExecutor.execute();
	}
}
