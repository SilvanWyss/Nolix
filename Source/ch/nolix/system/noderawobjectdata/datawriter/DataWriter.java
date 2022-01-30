//package declaration
package ch.nolix.system.noderawobjectdata.datawriter;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.noderawobjectdata.tabledefinition.TableInfo;
import ch.nolix.systemapi.rawobjectdataapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordDeletionDTO;
import ch.nolix.systemapi.rawobjectdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class DataWriter implements IDataWriter {
	
	//attributes
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
		//TODO: Implement.
	}
	
	//method
	@Override
	public void deleteEntryFromMultiValue(
		final String tableName,
		final String recordId,
		final String multiValueColumnName,
		final String entry
	) {
		//TODO: Implement.
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
		//TODO: Implement.
	}
	
	//method
	@Override
	public void insertRecordIntoTable(String tableName, IRecordDTO record) {
		internalDataWriter.insertRecordIntoTable(getTableDefinitionForTableWithName(tableName), record);
	}
	
	//method
	@Override
	public void saveChanges() {
		internalDataWriter.saveChanges();
	}
	
	//method
	@Override
	public void updateRecordOnTable(String tableName, IRecordUpdateDTO recordUpdate) {
		internalDataWriter.updateRecordOnTable(getTableDefinitionForTableWithName(tableName), recordUpdate);
	}
	
	//method
	private TableInfo getTableDefinitionForTableWithName(final String tableName) {
		return tableInfos.getRefFirst(td -> td.getTableName().equals(tableName));
	}
}
