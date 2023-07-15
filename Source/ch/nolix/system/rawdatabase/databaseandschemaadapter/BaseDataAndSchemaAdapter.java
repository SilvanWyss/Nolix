//package declaration
package ch.nolix.system.rawdatabase.databaseandschemaadapter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdatabaseapi.databaseadapterapi.IDatabaseAdapter;
import ch.nolix.systemapi.rawdatabaseapi.databaseandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDto;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public abstract class BaseDataAndSchemaAdapter implements IDataAndSchemaAdapter {
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//attribute
	private final IDatabaseAdapter databaseAdapter;
	
	//attribute
	private final ISchemaReader schemaReader;
	
	//constructor
	protected BaseDataAndSchemaAdapter(
		final IDatabaseAdapter databaseAdapter,
		final ISchemaReader schemaReader
	) {
		
		GlobalValidator.assertThat(databaseAdapter).thatIsNamed(IDatabaseAdapter.class).isNotNull();
		GlobalValidator.assertThat(schemaReader).thatIsNamed(ISchemaReader.class).isNotNull();
		
		this.databaseAdapter = databaseAdapter;
		this.schemaReader = schemaReader;
		
		getStoredCloseController().createCloseDependencyTo(databaseAdapter);
		getStoredCloseController().createCloseDependencyTo(schemaReader);
	}
	
	//method
	@Override
	public final boolean columnIsEmpty(final String tableName, final String columnName) {
		return schemaReader.columnIsEmpty(tableName, columnName);
	}
	
	//method
	@Override
	public final void deleteMultiReferenceEntries(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		databaseAdapter.deleteMultiReferenceEntries(tableName, entityId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public final void deleteMultiValueEntries(
		final String tableName,
		final String entityId,
		final String multiValueColumnName
	) {
		databaseAdapter.deleteMultiValueEntries(tableName, entityId, multiValueColumnName);
	}
	
	//method
	@Override
	public final void deleteMultiReferenceEntry(
		final String tableName,
		final String entityId,
		final String multiRefereceColumnName,
		final String referencedEntityId
	) {
		databaseAdapter.deleteMultiReferenceEntry(tableName, entityId, multiRefereceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public final void deleteMultiValueEntry(
		final String tableName,
		final String entityId,
		final String multiValueColumnName,
		final String entry
	) {
		databaseAdapter.deleteMultiValueEntry(tableName, entityId, multiValueColumnName, entry);
	}
	
	//method
	@Override
	public final void deleteEntity(final String tableName, final IEntityHeadDto entity) {
		databaseAdapter.deleteEntity(tableName, entity);
	}
	
	//method
	@Override
	public final void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
		databaseAdapter.expectGivenSchemaTimestamp(schemaTimestamp);
	}
	
	//method
	@Override
	public final void expectTableContainsEntity(final String tableName, final String entityId) {
		databaseAdapter.expectTableContainsEntity(tableName, entityId);
	}
	
	//method
	@Override
	public final CloseController getStoredCloseController() {
		return closeController;
	}
	
	//method
	@Override
	public final int getSaveCount() {
		return databaseAdapter.getSaveCount();
	}
	
	//method
	@Override
	public final ITime getSchemaTimestamp() {
		return databaseAdapter.getSchemaTimestamp();
	}
	
	//method
	@Override
	public final int getTableCount() {
		return schemaReader.getTableCount();
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return databaseAdapter.hasChanges();
	}
	
	//method
	@Override
	public final void insertMultiReferenceEntry(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName,
		final String referencedEntityId
	) {
		databaseAdapter.insertMultiReferenceEntry(tableName, entityId, multiReferenceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public final void insertMultiValueEntry(
		final String tableName,
		final String entityId,
		final String multiValueColumnName,
		final String entry
	) {
		databaseAdapter.insertMultiValueEntry(tableName, entityId, multiValueColumnName, entry);
	}
	
	//method
	@Override
	public final void insertNewEntity(final String tableName, final INewEntityDto newEntity) {
		databaseAdapter.insertNewEntity(tableName, newEntity);
	}
	
	//method
	@Override
	public final IContainer<String> loadMultiReferenceEntries(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		return databaseAdapter.loadMultiReferenceEntries(tableName, entityId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public final IContainer<Object> loadMultiValueEntries(
		final String tableName,
		final String entityId,
		final String multiFieldColumnName
	) {
		return databaseAdapter.loadMultiValueEntries(tableName, entityId, multiFieldColumnName);
	}
	
	//method	
	@Override
	public final IContainer<ILoadedEntityDto> loadEntitiesOfTable(final String tableName) {
		return databaseAdapter.loadEntitiesOfTable(tableName);
	}
	
	//method
	@Override
	public final IContainer<IColumnDto> loadColumnsByTableId(final String tableId) {
		return schemaReader.loadColumnsByTableId(tableId);
	}
	
	//method
	@Override
	public final IContainer<IColumnDto> loadColumnsByTableName(final String tableName) {
		return schemaReader.loadColumnsByTableName(tableName);
	}
	
	//method
	@Override
	public IFlatTableDto loadFlatTableById(String id) {
		return schemaReader.loadFlatTableById(id);
	}
	
	//method
	@Override
	public final IFlatTableDto loadFlatTableByName(final String name) {
		return schemaReader.loadFlatTableByName(name);
	}
	
	//method
	@Override
	public final IContainer<IFlatTableDto> loadFlatTables() {
		return schemaReader.loadFlatTables();
	}
	
	//method
	@Override
	public final ILoadedEntityDto loadEntity(final String tableName, final String id) {
		return databaseAdapter.loadEntity(tableName, id);
	}
	
	//method
	@Override
	public final ITime loadSchemaTimestamp() {
		return schemaReader.loadSchemaTimestamp();
	}
	
	//method
	@Override
	public final ITableDto loadTableById(final String id) {
		return schemaReader.loadTableById(id);
	}
	
	//method
	@Override
	public final ITableDto loadTableByName(final String name) {
		return schemaReader.loadTableByName(name);
	}
	
	//method
	@Override
	public final IContainer<ITableDto> loadTables() {
		return schemaReader.loadTables();
	}
	
	//method
	@Override
	public final void noteClose() {}
	
	//method
	@Override
	public final void reset() {
		databaseAdapter.reset();
	}
	
	//method
	@Override
	public final void saveChanges() {
		databaseAdapter.saveChanges();
	}
	
	//method
	@Override
	public final void setEntityAsUpdated(final String tableName, final IEntityHeadDto entity) {
		databaseAdapter.setEntityAsUpdated(tableName, entity);
	}
	
	//method
	@Override
	public final boolean tableContainsEntityWithGivenValueAtGivenColumn(
		final String tableName,
		final String columnName,
		final String value
	) {
		return databaseAdapter.tableContainsEntityWithGivenValueAtGivenColumn(tableName, columnName, value);
	}
	
	//method
	@Override
	public final boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
		return databaseAdapter.tableContainsEntityWithGivenId(tableName, id);
	}
	
	//method
	@Override
	public final void updateEntity(final String tableName, final IEntityUpdateDto entityUpdate) {
		databaseAdapter.updateEntity(tableName, entityUpdate);
	}
}
