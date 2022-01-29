//package declaration
package ch.nolix.system.sqlrawobjectdata.datawriter;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.sql.SQLConnection;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IColumnDefinition;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IRecordStatementCreator;
import ch.nolix.system.sqlrawobjectdata.sqlapi.ITableDefinition;
import ch.nolix.systemapi.rawobjectdataapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class DataWriter implements IDataWriter {
	
	//attribute
	private final InternalDataWriter internalDataWriter;
	
	//multi-attribute
	private final IContainer<ITableDefinition> tableDefinitions;
	
	//constructor
	public DataWriter(
		final SQLConnection pSQLConnection,
		final IContainer<ITableDefinition> tableDefinitions,
		final IRecordStatementCreator recordStatementCreator
	) {
		
		Validator.assertThat(tableDefinitions).thatIsNamed("table definitions").isNotNull();
		
		internalDataWriter = new InternalDataWriter(pSQLConnection, recordStatementCreator);		
		this.tableDefinitions = tableDefinitions;
	}
	
	//method
	@Override
	public void deleteEntriesFromMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName
	) {
		internalDataWriter.deleteEntriesFromMultiValue(
			recordId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId()
		);
	}
	
	//method
	@Override
	public void deleteEntryFromMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName,
		final String entry
	) {
		internalDataWriter.deleteEntryFromMultiValue(
			recordId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId(),
			entry
		);
	}
	
	//method
	@Override
	public void deleteRecordFromTable(final String tableName, final IRecordDeletionDTO recordDeletion) {
		internalDataWriter.deleteRecordFromTable(tableName, recordDeletion);
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return internalDataWriter.hasChanges();
	}
	
	//method
	@Override
	public void insertEntryIntoMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName,
		final String entry
	) {
		internalDataWriter.insertEntryIntoMultiValue(
			recordId,
			getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).getColumnId(),
			entry
		);
	}
	
	//method
	@Override
	public void insertRecordIntoTable(final String tableName, final IRecordDTO record) {
		internalDataWriter.insertRecordIntoTable(tableName, record);
	}
	
	//method
	@Override
	public void updateRecordOnTable(final String tableName, final IRecordUpdateDTO recordUpdate) {
		internalDataWriter.updateRecordOnTable(tableName, recordUpdate);
	}
	
	//method
	@Override
	public void saveChanges() {
		internalDataWriter.saveChanges();
	}
	
	//method
	private IColumnDefinition getColumnDefinitionByTableNameAndColumnName(
		final String tableName,
		final String columnName
	) {
		return getTableDefinitionByTableName(tableName).getColumnDefinitionByColumnName(columnName);
	}
	
	//method
	private ITableDefinition getTableDefinitionByTableName(final String tableName) {
		return tableDefinitions.getRefFirstOrNull(td -> td.getTableName().equals(tableName));
	}
}
