//package declaration
package ch.nolix.system.sqlrawobjectschema.schemawriter;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.requestapi.ChangeRequestable;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.ITableDTO;

//class
final class SystemDataWriter implements ChangeRequestable {
	
	//constant
	private final SystemDataWriterSQLStatementCreator systemDataWriterSQLStatementCreator =
	new SystemDataWriterSQLStatementCreator();
	
	//attribute
	private final LinkedList<String> mSQLStatements = new LinkedList<>();
		
	//method
	public void addColumn(final String tableName, final IColumnDTO column) {
		mSQLStatements.addAtEnd(
			systemDataWriterSQLStatementCreator.createStatementToAddColumn(tableName, column)
		);
	}
	
	//method
	public void deleteColumn(String tableName, String columnName) {
		mSQLStatements.addAtEnd(
			systemDataWriterSQLStatementCreator.createStatementToDeleteColumn(tableName, columnName)
		);
	}
	
	//method
	public void addTable(final ITableDTO table) {
		mSQLStatements.addAtEnd(systemDataWriterSQLStatementCreator.createStatementsToAddTable(table));
	}
	
	//method
	public void deleteTable(final String tableName) {
		mSQLStatements.addAtEnd(systemDataWriterSQLStatementCreator.createStatementToDeleteTable(tableName));
	}
	
	//method
	public IContainer<String> getSQLStatements() {
		return mSQLStatements;
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return mSQLStatements.containsAny();
	}
	
	//method
	public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
		mSQLStatements.addAtEnd(
			systemDataWriterSQLStatementCreator.createStatementToSetColumnName(
				tableName,
				columnName,
				newColumnName
			)
		);
	}
	
	//method
	public void setColumnParametrizedPropertyType(
		final String tableName,
		final String columnName,
		final IParametrizedPropertyTypeDTO parametrizedPropertyType
	) {
		mSQLStatements.addAtEnd(
			systemDataWriterSQLStatementCreator.createStatementToSetColumnParametrizedPropertyType(
				tableName,
				columnName,
				parametrizedPropertyType
			)
		);
	}
	
	//method
	public void setSchemaTimestamp(Time schemaTimestamp) {
		mSQLStatements.addAtEnd(
			systemDataWriterSQLStatementCreator.createStatementToSetSchemaTimestamp(schemaTimestamp)
		);
	}
	
	//method
	public void setTableName(final String tableName, final String newTableName) {
		mSQLStatements.addAtEnd(
			systemDataWriterSQLStatementCreator.createStatementToSetTableName(tableName, newTableName)
		);
	}
}
