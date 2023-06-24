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
		sqlCollector.addSqlStatement(
			systemDataWriterSqlStatementCreator.createStatementToAddColumn(tableName, column)
		);
	}
	
	//method
	public void deleteColumn(String tableName, String columnName) {
		sqlCollector.addSqlStatement(
			systemDataWriterSqlStatementCreator.createStatementToDeleteColumn(tableName, columnName)
		);
	}
	
	//method
	public void addTable(final ITableDTO table) {
		sqlCollector.addSqlStatements(systemDataWriterSqlStatementCreator.createStatementsToAddTable(table));
	}
	
	//method
	public void deleteTable(final String tableName) {
		sqlCollector.addSqlStatement(systemDataWriterSqlStatementCreator.createStatementToDeleteTable(tableName));
	}
		
	//method
	@Override
	public boolean hasChanges() {
		return sqlCollector.containsAny();
	}
	
	//method
	public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
		sqlCollector.addSqlStatement(
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
		sqlCollector.addSqlStatement(
			systemDataWriterSqlStatementCreator.createStatementToSetColumnParametrizedPropertyType(
				columnId,
				parametrizedPropertyType
			)
		);
	}
	
	//method
	public void setSchemaTimestamp(ITime schemaTimestamp) {
		sqlCollector.addSqlStatement(
			systemDataWriterSqlStatementCreator.createStatementToSetSchemaTimestamp(schemaTimestamp)
		);
	}
	
	//method
	public void setTableName(final String tableName, final String newTableName) {
		sqlCollector.addSqlStatement(
			systemDataWriterSqlStatementCreator.createStatementToSetTableName(tableName, newTableName)
		);
	}
}
