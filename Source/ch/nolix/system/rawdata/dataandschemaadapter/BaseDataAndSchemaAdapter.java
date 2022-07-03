//package declaration
package ch.nolix.system.rawdata.dataandschemaadapter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedRecordDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordDTO;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IRecordUpdateDTO;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public abstract class BaseDataAndSchemaAdapter implements IDataAndSchemaAdapter {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//attribute
	private final IDataAdapter dataAdapter;
	
	//attribute
	private final ISchemaReader schemaReader;
	
	//constructor
	protected BaseDataAndSchemaAdapter(
		final IDataAdapter dataAdapter,
		final ISchemaReader schemaReader
	) {
		
		GlobalValidator.assertThat(dataAdapter).thatIsNamed(IDataAdapter.class).isNotNull();
		GlobalValidator.assertThat(schemaReader).thatIsNamed(ISchemaReader.class).isNotNull();
		
		this.dataAdapter = dataAdapter;
		this.schemaReader = schemaReader;
		
		getRefCloseController().createCloseDependencyTo(dataAdapter);
		getRefCloseController().createCloseDependencyTo(schemaReader);
	}
	
	//method
	@Override
	public final boolean columnIsEmpty(final String tableName, final String columnName) {
		return schemaReader.columnIsEmpty(tableName, columnName);
	}
	
	//method
	@Override
	public final void deleteEntriesFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		dataAdapter.deleteEntriesFromMultiReference(tableName, entityId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public final void deleteEntriesFromMultiValue(
		final String tableName,
		final String entityId,
		final String multiValueColumnName
	) {
		dataAdapter.deleteEntriesFromMultiValue(tableName, entityId, multiValueColumnName);
	}
	
	//method
	@Override
	public final void deleteEntryFromMultiReference(
		final String tableName,
		final String entityId,
		final String multiRefereceColumnName,
		final String referencedEntityId
	) {
		dataAdapter.deleteEntryFromMultiReference(tableName, entityId, multiRefereceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public final void deleteEntryFromMultiValue(
		final String tableName,
		final String entityId,
		final String multiValueColumnName,
		final String entry
	) {
		dataAdapter.deleteEntryFromMultiValue(tableName, entityId, multiValueColumnName, entry);
	}
	
	//method
	@Override
	public final void deleteRecordFromTable(final String tableName, final IEntityHeadDTO entity) {
		dataAdapter.deleteRecordFromTable(tableName, entity);
	}
	
	//method
	@Override
	public final void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
		dataAdapter.expectGivenSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public final int getSaveCount() {
		return dataAdapter.getSaveCount();
	}
	
	//method
	@Override
	public final ITime getSchemaTimestamp() {
		return dataAdapter.getSchemaTimestamp();
	}
	
	//method
	@Override
	public final int getTableCount() {
		return schemaReader.getTableCount();
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return dataAdapter.hasChanges();
	}
	
	//method
	@Override
	public final void insertEntryIntoMultiReference(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName,
		final String referencedEntityId
	) {
		dataAdapter.insertEntryIntoMultiReference(tableName, entityId, multiReferenceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public final void insertEntryIntoMultiValue(
		final String tableName,
		final String entityId,
		final String multiValueColumnName,
		final String entry
	) {
		dataAdapter.insertEntryIntoMultiValue(tableName, entityId, multiValueColumnName, entry);
	}
	
	//method
	@Override
	public final void insertRecordIntoTable(final String tableName, final IRecordDTO pRecord) {
		dataAdapter.insertRecordIntoTable(tableName, pRecord);
	}
	
	//method
	@Override
	public final IContainer<String> loadAllMultiReferenceEntriesForRecord(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		return dataAdapter.loadAllMultiReferenceEntriesForRecord(tableName, entityId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public final IContainer<Object> loadAllMultiValueEntriesFromRecord(
		final String tableName,
		final String entityId,
		final String multiFieldColumnName
	) {
		return dataAdapter.loadAllMultiValueEntriesFromRecord(tableName, entityId, multiFieldColumnName);
	}
	
	//method	
	@Override
	public final IContainer<ILoadedRecordDTO> loadAllRecordsFromTable(final String tableName) {
		return dataAdapter.loadAllRecordsFromTable(tableName);
	}
	
	//method
	@Override
	public final IContainer<IColumnDTO> loadColumnsByTableId(final String tableId) {
		return schemaReader.loadColumnsByTableId(tableId);
	}
	
	//method
	@Override
	public final IContainer<IColumnDTO> loadColumnsByTableName(final String tableName) {
		return schemaReader.loadColumnsByTableName(tableName);
	}
	
	//method
	@Override
	public IFlatTableDTO loadFlatTableById(String id) {
		return schemaReader.loadFlatTableById(id);
	}
	
	//method
	@Override
	public final IFlatTableDTO loadFlatTableByName(final String name) {
		return schemaReader.loadFlatTableByName(name);
	}
	
	//method
	@Override
	public final IContainer<IFlatTableDTO> loadFlatTables() {
		return schemaReader.loadFlatTables();
	}
	
	//method
	@Override
	public final ILoadedRecordDTO loadRecordFromTableById(final String tableName, final String id) {
		return dataAdapter.loadRecordFromTableById(tableName, id);
	}
	
	//method
	@Override
	public final ITime loadSchemaTimestamp() {
		return schemaReader.loadSchemaTimestamp();
	}
	
	//method
	@Override
	public final ITableDTO loadTableById(final String id) {
		return schemaReader.loadTableById(id);
	}
	
	//method
	@Override
	public final ITableDTO loadTableByName(final String name) {
		return schemaReader.loadTableByName(name);
	}
	
	//method
	@Override
	public final IContainer<ITableDTO> loadTables() {
		return schemaReader.loadTables();
	}
	
	//method
	@Override
	public final void noteClose() {}
	
	//method
	@Override
	public final void reset() {
		dataAdapter.reset();
	}
	
	//method
	@Override
	public final void saveChangesAndReset() {
		dataAdapter.saveChangesAndReset();
	}
	
	//method
	@Override
	public final void setEntityAsUpdated(final String tableName, final IEntityHeadDTO entity) {
		dataAdapter.setEntityAsUpdated(tableName, entity);
	}
	
	//method
	@Override
	public final boolean tableContainsEntityWithGivenValueAtGivenColumn(
		final String tableName,
		final String columnName,
		final String value
	) {
		return dataAdapter.tableContainsEntityWithGivenValueAtGivenColumn(tableName, columnName, value);
	}
	
	//method
	@Override
	public final void updateRecordOnTable(final String tableName, final IRecordUpdateDTO recordUpdate) {
		dataAdapter.updateRecordOnTable(tableName, recordUpdate);
	}
}
