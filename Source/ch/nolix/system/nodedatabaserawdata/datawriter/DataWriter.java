//package declaration
package ch.nolix.system.nodedatabaserawdata.datawriter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.TableInfo;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;

//class
public final class DataWriter implements IDataWriter {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
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
	public void deleteEntriesFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		internalDataWriter.deleteEntriesFromMultiReference(
			getTableInfoByTableName(tableName),
			entityId,
			multiReferenceColumnName
		);
	}
	
	//method
	@Override
	public void deleteEntriesFromMultiValue(
		final String tableName,
		final String entityId,
		final String multiValueColumnName
	) {
		internalDataWriter.deleteEntriesFromMultiValue(
			getTableInfoByTableName(tableName),
			entityId,
			multiValueColumnName
		);
	}
	
	//method
	@Override
	public void deleteEntryFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiRefereceColumnName,
		final String referencedEntityId
	) {
		internalDataWriter.deleteEntryFromMultiReference(
			getTableInfoByTableName(tableName),
			entityId,
			multiRefereceColumnName,
			referencedEntityId
		);
	}
	
	//method
	@Override
	public void deleteEntryFromMultiValue(
		final String tableName,
		final String entityId,
		final String multiValueColumnName,
		final String entry
	) {
		internalDataWriter.deleteEntryFromMultiValue(
			getTableInfoByTableName(tableName),
			entityId,
			multiValueColumnName,
			entry
		);
	}
	
	//method
	@Override
	public void deleteRecordFromTable(final String tableName, final IEntityHeadDTO entity) {
		internalDataWriter.deleteRecordFromTable(tableName, entity);
	}
	
	//method
	@Override
	public int getSaveCount() {
		return internalDataWriter.getSaveCount();
	}
	
	//method
	@Override
	public boolean hasChanges() {
		return internalDataWriter.hasChanges();
	}
	
	//method
	@Override
	public void insertEntryIntoMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName,
		final String referencedEntityId
	) {
		internalDataWriter.insertEntryIntoMultiReference(
			getTableInfoByTableName(tableName),
			entityId,
			multiReferenceColumnName,
			referencedEntityId
		);
	}
	
	//method
	@Override
	public void insertEntryIntoMultiValue(
		final String tableName,
		final String entityId,
		final String multiValueColumnName,
		final String entry
	) {
		internalDataWriter.insertEntryIntoMultiValue(
			getTableInfoByTableName(tableName),
			entityId,
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
	public CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public void noteClose() {}
	
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
	public void setEntityAsUpdated(final String tableName, final IEntityHeadDTO entity) {
		internalDataWriter.setEntityAsUpdated(tableName, entity);
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
