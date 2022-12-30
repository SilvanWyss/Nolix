//package declaration
package ch.nolix.system.rawdatabase.databaseadapter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi.IDatabaseAdapter;
import ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi.IDatabaseReader;
import ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi.IDatabaseWriter;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDTO;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public abstract class BaseDatabaseAdapter implements IDatabaseAdapter {
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//attribute
	private final IDatabaseReader databaseReader;
	
	//attribute
	private final IDatabaseWriter databaseWriter;
	
	//constructor
	protected BaseDatabaseAdapter(final IDatabaseReader databaseReader, final IDatabaseWriter databaseWriter) {
		
		GlobalValidator.assertThat(databaseReader).thatIsNamed(IDatabaseReader.class).isNotNull();
		GlobalValidator.assertThat(databaseWriter).thatIsNamed(IDatabaseWriter.class).isNotNull();
		
		this.databaseReader = databaseReader;
		this.databaseWriter = databaseWriter;
		
		getRefCloseController().createCloseDependencyTo(databaseReader);
		getRefCloseController().createCloseDependencyTo(databaseWriter);
	}
	
	//method
	@Override
	public final void deleteEntriesFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		databaseWriter.deleteEntriesFromMultiReference(tableName, entityId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public final void deleteEntriesFromMultiValue(
		final String tableName,
		final String entityId,
		final String multiFieldColumn
	) {
		databaseWriter.deleteEntriesFromMultiValue(tableName, entityId, multiFieldColumn);
	}
	
	//method
	@Override
	public final void deleteEntryFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiRefereceColumnName,
		final String referencedEntityId
	) {
		databaseWriter.deleteEntryFromMultiReference(tableName, entityId, multiRefereceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public final void deleteEntryFromMultiValue(
		final String tableName,
		final String entityId,
		final String multiFieldColumn,
		final String entry
	) {
		databaseWriter.deleteEntryFromMultiValue(tableName, entityId, multiFieldColumn, entry);
	}
	
	//method
	@Override
	public final void deleteRecordFromTable(final String tableName, final IEntityHeadDTO entity) {
		databaseWriter.deleteRecordFromTable(tableName, entity);
	}
	
	//method
	@Override
	public final void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
		databaseWriter.expectGivenSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public final void expectTableContainsEntity(final String tableName, final String entityId) {
		databaseWriter.expectTableContainsEntity(tableName, entityId);
	}
	
	//method
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public final int getSaveCount() {
		return databaseWriter.getSaveCount();
	}
	
	//method
	@Override
	public final ITime getSchemaTimestamp() {
		return databaseReader.getSchemaTimestamp();
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return databaseWriter.hasChanges();
	}
	
	//method
	@Override
	public final void insertEntryIntoMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName,
		final String referencedEntityId
	) {
		databaseWriter.insertEntryIntoMultiReference(tableName, entityId, multiReferenceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public final void insertEntryIntoMultiValue(
		final String tableName,
		final String entityId,
		final String multiFieldColumn,
		final String entry
	) {
		databaseWriter.insertEntryIntoMultiValue(tableName, entityId, multiFieldColumn, entry);
	}
	
	//method
	@Override
	public final void insertRecordIntoTable(final String tableName, final INewEntityDTO pRecord) {
		databaseWriter.insertRecordIntoTable(tableName, pRecord);
	}
	
	@Override
	public final IContainer<String> loadEntriesOfMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		return databaseReader.loadEntriesOfMultiReference(tableName, entityId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public final IContainer<Object> loadEntriesOfMultiValue(
		final String tableName,
		final String entityId,
		final String multiFieldColumnName
	) {
		return databaseReader.loadEntriesOfMultiValue(tableName, entityId, multiFieldColumnName);
	}
	
	//method	
	@Override
	public final IContainer<ILoadedEntityDTO> loadEntitiesOfTable(final String tableName) {
		return databaseReader.loadEntitiesOfTable(tableName);
	}
	
	//method
	@Override
	public final ILoadedEntityDTO loadEntityOfTableById(final String tableName, final String id) {
		return databaseReader.loadEntityOfTableById(tableName, id);
	}
	
	//method
	@Override
	public void noteClose() {}
	
	//method
	@Override
	public final void reset() {
		databaseWriter.reset();
	}
	
	//method
	@Override
	public final void saveChangesAndReset() {
		databaseWriter.saveChangesAndReset();
	}
	
	//method
	@Override
	public final void setEntityAsUpdated(final String tableName, final IEntityHeadDTO entity) {
		databaseWriter.setEntityAsUpdated(tableName, entity);
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
	public final void updateEntityOnTable(final String tableName, final IEntityUpdateDTO recordUpdate) {
		databaseWriter.updateEntityOnTable(tableName, recordUpdate);
	}
}
