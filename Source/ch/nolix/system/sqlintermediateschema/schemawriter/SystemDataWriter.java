//package declaration
package ch.nolix.system.sqlintermediateschema.schemawriter;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.requestapi.ChangeRequestable;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IParametrizedPropertyTypeDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.ITableDTO;

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
	public void deleteColumn(String tableName, String columnHeader) {
		mSQLStatements.addAtEnd(
			systemDataWriterSQLStatementCreator.createStatementToDeleteColumn(tableName, columnHeader)
		);
	}
	
	//method
	public void addTable(final ITableDTO table) {
		mSQLStatements.addAtEnd(systemDataWriterSQLStatementCreator.createStatementToAddTable(table));
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
	public void setColumnHeader(final String tableName, final String columnHeader, final String newColumnHeader) {
		mSQLStatements.addAtEnd(
			systemDataWriterSQLStatementCreator.createStatementToSetColumnHeader(
				tableName,
				columnHeader,
				newColumnHeader
			)
		);
	}
	
	//method
	public void setColumnParametrizedPropertyType(
		final String tableName,
		final String columnHeader,
		final IParametrizedPropertyTypeDTO parametrizedPropertyType
	) {
		mSQLStatements.addAtEnd(
			systemDataWriterSQLStatementCreator.createStatementToSetColumnParametrizedPropertyType(
				tableName,
				columnHeader,
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
