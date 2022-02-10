//package declaration
package ch.nolix.system.noderawobjectdata.datawriter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableInfo;
import ch.nolix.systemapi.rawobjectdataapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordHeadDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class DataWriter implements IDataWriter {
	
	//attribute
	private final InternalDataWriter internalDataWriter;
	
	//multi-attribute
	private final IContainer<TableInfo> tableInfos;
	
	//constructor
	public DataWriter(final BaseNode nodeDatabase, final IContainer<TableInfo> tableInfos) {
		
		Validator.assertThat(tableInfos).thatIsNamed("table definitions").isNotNull();
		
		internalDataWriter = new InternalDataWriter(nodeDatabase);
		this.tableInfos = tableInfos;		
	}
	
	//method
	@Override
	public void deleteEntriesFromMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName
	) {
		internalDataWriter.deleteEntriesFromMultiValue(
			getTableInfoByTableName(tableName),
			recordId,
			multiValueColumnName
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
			getTableInfoByTableName(tableName),
			recordId,
			multiValueColumnName,
			entry
		);
	}
	
	//method
	@Override
	public void deleteRecordFromTable(final String tableName, final IRecordHeadDTO recordHead) {
		internalDataWriter.deleteRecordFromTable(tableName, recordHead);
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
			getTableInfoByTableName(tableName),
			recordId,
			multiValueColumnName,
			entry
		);
	}
	
	//method
	@Override
	public void insertRecordIntoTable(final String tableName, final IRecordDTO record) {
		internalDataWriter.insertRecordIntoTable(getTableInfoByTableName(tableName), record);
	}
	
	//method
	@Override
	public void reset() {
		internalDataWriter.reset();
	}
	
	//method
	@Override
	public void saveChangesAndReset() {
		internalDataWriter.saveChangesAndReset();
	}
	
	//method
	@Override
	public void updateRecordOnTable(final String tableName, final IRecordUpdateDTO recordUpdate) {
		internalDataWriter.updateRecordOnTable(getTableInfoByTableName(tableName), recordUpdate);
	}
	
	//method
	private TableInfo getTableInfoByTableName(final String tableName) {
		return tableInfos.getRefFirst(td -> td.getTableName().equals(tableName));
	}
}
