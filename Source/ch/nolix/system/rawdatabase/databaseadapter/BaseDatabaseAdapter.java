//package declaration
package ch.nolix.system.rawdatabase.databaseadapter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi.IDatabaseAdapter;
import ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi.IDatabaseReader;
import ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi.IDataWriter;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IRecordUpdateDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public abstract class BaseDatabaseAdapter implements IDatabaseAdapter {
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//attribute
	private final IDatabaseReader databaseReader;
	
	//attribute
	private final IDataWriter dataWriter;
	
	//constructor
	protected BaseDatabaseAdapter(final IDatabaseReader databaseReader, final IDataWriter dataWriter) {
		
		GlobalValidator.assertThat(databaseReader).thatIsNamed(IDatabaseReader.class).isNotNull();
		GlobalValidator.assertThat(dataWriter).thatIsNamed(IDataWriter.class).isNotNull();
		
		this.databaseReader = databaseReader;
		this.dataWriter = dataWriter;
		
		getRefCloseController().createCloseDependencyTo(databaseReader);
		getRefCloseController().createCloseDependencyTo(dataWriter);
	}
	
	//method
	@Override
	public final void deleteEntriesFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		dataWriter.deleteEntriesFromMultiReference(tableName, entityId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public final void deleteEntriesFromMultiValue(
		final String tableName,
		final String entityId,
		final String multiFieldColumn
	) {
		dataWriter.deleteEntriesFromMultiValue(tableName, entityId, multiFieldColumn);
	}
	
	//method
	@Override
	public final void deleteEntryFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiRefereceColumnName,
		final String referencedEntityId
	) {
		dataWriter.deleteEntryFromMultiReference(tableName, entityId, multiRefereceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public final void deleteEntryFromMultiValue(
		final String tableName,
		final String entityId,
		final String multiFieldColumn,
		final String entry
	) {
		dataWriter.deleteEntryFromMultiValue(tableName, entityId, multiFieldColumn, entry);
	}
	
	//method
	@Override
	public final void deleteRecordFromTable(final String tableName, final IEntityHeadDTO entity) {
		dataWriter.deleteRecordFromTable(tableName, entity);
	}
	
	//method
	@Override
	public final void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
		dataWriter.expectGivenSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public final int getSaveCount() {
		return dataWriter.getSaveCount();
	}
	
	//method
	@Override
	public final ITime getSchemaTimestamp() {
		return databaseReader.getSchemaTimestamp();
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return dataWriter.hasChanges();
	}
	
	//method
	@Override
	public final void insertEntryIntoMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName,
		final String referencedEntityId
	) {
		dataWriter.insertEntryIntoMultiReference(tableName, entityId, multiReferenceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public final void insertEntryIntoMultiValue(
		final String tableName,
		final String entityId,
		final String multiFieldColumn,
		final String entry
	) {
		dataWriter.insertEntryIntoMultiValue(tableName, entityId, multiFieldColumn, entry);
	}
	
	//method
	@Override
	public final void insertRecordIntoTable(final String tableName, final IRecordDTO pRecord) {
		dataWriter.insertRecordIntoTable(tableName, pRecord);
	}
	
	@Override
	public final IContainer<String> loadAllMultiReferenceEntriesForRecord(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		return databaseReader.loadAllMultiReferenceEntriesForRecord(tableName, entityId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public final IContainer<Object> loadAllMultiValueEntriesFromRecord(
		final String tableName,
		final String entityId,
		final String multiFieldColumnName
	) {
		return databaseReader.loadAllMultiValueEntriesFromRecord(tableName, entityId, multiFieldColumnName);
	}
	
	//method	
	@Override
	public final IContainer<ILoadedRecordDTO> loadAllRecordsFromTable(final String tableName) {
		return databaseReader.loadAllRecordsFromTable(tableName);
	}
	
	//method
	@Override
	public final ILoadedRecordDTO loadRecordFromTableById(final String tableName, final String id) {
		return databaseReader.loadRecordFromTableById(tableName, id);
	}
	
	//method
	@Override
	public void noteClose() {}
	
	//method
	@Override
	public final void reset() {
		dataWriter.reset();
	}
	
	//method
	@Override
	public final void saveChangesAndReset() {
		dataWriter.saveChangesAndReset();
	}
	
	//method
	@Override
	public final void setEntityAsUpdated(final String tableName, final IEntityHeadDTO entity) {
		dataWriter.setEntityAsUpdated(tableName, entity);
	}
	
	//method
	@Override
	public final boolean tableContainsEntityWithGivenValueAtGivenColumn(
		final String tableName,
		final String columnName,
		final String value
	) {
		return databaseReader.tableContainsEntityWithGivenValueAtGivenColumn(tableName, columnName, value);
	}
	
	//method
	@Override
	public final void updateRecordOnTable(final String tableName, final IRecordUpdateDTO recordUpdate) {
		dataWriter.updateRecordOnTable(tableName, recordUpdate);
	}
}
