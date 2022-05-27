//package declaration
package ch.nolix.system.sqlrawschema.schemawriter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.requestuniversalapi.ChangeRequestable;
import ch.nolix.core.sql.SQLCollector;
import ch.nolix.system.time.base.Time;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
final class SystemDataWriter implements ChangeRequestable {
	
	//static attribute
	private final SystemDataWriterSQLStatementCreator systemDataWriterSQLStatementCreator =
	new SystemDataWriterSQLStatementCreator();
	
	//attribute
	private final SQLCollector mSQLCollector;
	
	//constructor
	public SystemDataWriter(final SQLCollector pSQLCollector) {
		
		GlobalValidator.assertThat(pSQLCollector).thatIsNamed(SQLCollector.class).isNotNull();
		
		mSQLCollector = pSQLCollector;
	}
		
	//method
	public void addColumn(final String tableName, final IColumnDTO column) {
		mSQLCollector.addSQLStatement(
			systemDataWriterSQLStatementCreator.createStatementToAddColumn(tableName, column)
		);
	}
	
	//method
	public void deleteColumn(String tableName, String columnName) {
		mSQLCollector.addSQLStatement(
			systemDataWriterSQLStatementCreator.createStatementToDeleteColumn(tableName, columnName)
		);
	}
	
	//method
	public void addTable(final ITableDTO table) {
		mSQLCollector.addSQLStatements(systemDataWriterSQLStatementCreator.createStatementsToAddTable(table));
	}
	
	//method
	public void deleteTable(final String tableName) {
		mSQLCollector.addSQLStatement(systemDataWriterSQLStatementCreator.createStatementToDeleteTable(tableName));
	}
		
	//method
	@Override
	public boolean hasChanges() {
		return mSQLCollector.containsAny();
	}
	
	//method
	public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
		mSQLCollector.addSQLStatement(
			systemDataWriterSQLStatementCreator.createStatementToSetColumnName(
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
			systemDataWriterSQLStatementCreator.createStatementToSetColumnParametrizedPropertyType(
				columnId,
				parametrizedPropertyType
			)
		);
	}
	
	//method
	public void setSchemaTimestamp(Time schemaTimestamp) {
		mSQLCollector.addSQLStatement(
			systemDataWriterSQLStatementCreator.createStatementToSetSchemaTimestamp(schemaTimestamp)
		);
	}
	
	//method
	public void setTableName(final String tableName, final String newTableName) {
		mSQLCollector.addSQLStatement(
			systemDataWriterSQLStatementCreator.createStatementToSetTableName(tableName, newTableName)
		);
	}
}
