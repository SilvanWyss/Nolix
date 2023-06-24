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
	private final SqlCollector sqlCollector;
	
	//constructor
	public SystemDataWriter(final SqlCollector sqlCollector) {
		
		GlobalValidator.assertThat(sqlCollector).thatIsNamed(SqlCollector.class).isNotNull();
		
		this.sqlCollector = sqlCollector;
	}
	
	//method
	public void addColumn(final String tableName, final IColumnDTO column) {
		sqlCollector.addSQLStatement(
			systemDataWriterSqlStatementCreator.createStatementToAddColumn(tableName, column)
		);
	}
	
	//method
	public void deleteColumn(String tableName, String columnName) {
		sqlCollector.addSQLStatement(
			systemDataWriterSqlStatementCreator.createStatementToDeleteColumn(tableName, columnName)
		);
	}
	
	//method
	public void addTable(final ITableDTO table) {
		sqlCollector.addSQLStatements(systemDataWriterSqlStatementCreator.createStatementsToAddTable(table));
	}
	
	//method
	public void deleteTable(final String tableName) {
		sqlCollector.addSQLStatement(systemDataWriterSqlStatementCreator.createStatementToDeleteTable(tableName));
	}
		
	//method
	@Override
	public boolean hasChanges() {
		return sqlCollector.containsAny();
	}
	
	//method
	public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
		sqlCollector.addSQLStatement(
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
		sqlCollector.addSQLStatement(
			systemDataWriterSqlStatementCreator.createStatementToSetColumnParametrizedPropertyType(
				columnId,
				parametrizedPropertyType
			)
		);
	}
	
	//method
	public void setSchemaTimestamp(ITime schemaTimestamp) {
		sqlCollector.addSQLStatement(
			systemDataWriterSqlStatementCreator.createStatementToSetSchemaTimestamp(schemaTimestamp)
		);
	}
	
	//method
	public void setTableName(final String tableName, final String newTableName) {
		sqlCollector.addSQLStatement(
			systemDataWriterSqlStatementCreator.createStatementToSetTableName(tableName, newTableName)
		);
	}
}
