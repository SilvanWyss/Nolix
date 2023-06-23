//package declaration
package ch.nolix.system.sqldatabaserawschema.schemawriter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.SqlCollector;
import ch.nolix.coreapi.functionapi.requestuniversalapi.ChangeRequestable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
final class SystemDataWriter implements ChangeRequestable {
	
	//static attribute
	private final SystemDataWriterSqlStatementCreator systemDataWriterSqlStatementCreator =
	new SystemDataWriterSqlStatementCreator();
	
	//attribute
	private final SqlCollector mSQLCollector;
	
	//constructor
	public SystemDataWriter(final SqlCollector pSQLCollector) {
		
		GlobalValidator.assertThat(pSQLCollector).thatIsNamed(SqlCollector.class).isNotNull();
		
		mSQLCollector = pSQLCollector;
	}
		
	//method
	public void addColumn(final String tableName, final IColumnDTO column) {
		mSQLCollector.addSQLStatement(
			systemDataWriterSqlStatementCreator.createStatementToAddColumn(tableName, column)
		);
	}
	
	//method
	public void deleteColumn(String tableName, String columnName) {
		mSQLCollector.addSQLStatement(
			systemDataWriterSqlStatementCreator.createStatementToDeleteColumn(tableName, columnName)
		);
	}
	
	//method
	public void addTable(final ITableDTO table) {
		mSQLCollector.addSQLStatements(systemDataWriterSqlStatementCreator.createStatementsToAddTable(table));
	}
	
	//method
	public void deleteTable(final String tableName) {
		mSQLCollector.addSQLStatement(systemDataWriterSqlStatementCreator.createStatementToDeleteTable(tableName));
	}
		
	//method
	@Override
	public boolean hasChanges() {
		return mSQLCollector.containsAny();
	}
	
	//method
	public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
		mSQLCollector.addSQLStatement(
			systemDataWriterSqlStatementCreator.createStatementToSetColumnName(
				tableName,
				columnName,
				newColumnName
			)
		);
	}
	
	//method
	public void setColumnParametrizedPropertyType(
		final String columnId,
		final IParametrizedPropertyTypeDTO parametrizedPropertyType
	) {
		mSQLCollector.addSQLStatement(
			systemDataWriterSqlStatementCreator.createStatementToSetColumnParametrizedPropertyType(
				columnId,
				parametrizedPropertyType
			)
		);
	}
	
	//method
	public void setSchemaTimestamp(ITime schemaTimestamp) {
		mSQLCollector.addSQLStatement(
			systemDataWriterSqlStatementCreator.createStatementToSetSchemaTimestamp(schemaTimestamp)
		);
	}
	
	//method
	public void setTableName(final String tableName, final String newTableName) {
		mSQLCollector.addSQLStatement(
			systemDataWriterSqlStatementCreator.createStatementToSetTableName(tableName, newTableName)
		);
	}
}
