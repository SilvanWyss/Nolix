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
	public final void deleteMultiReferenceEntries(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		databaseWriter.deleteMultiReferenceEntries(tableName, entityId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public final void deleteMultiValueEntries(
		final String tableName,
		final String entityId,
		final String multiFieldColumn
	) {
		databaseWriter.deleteMultiValueEntries(tableName, entityId, multiFieldColumn);
	}
	
	//method
	@Override
	public final void deleteMultiReferenceEntry(
		final String tableName,
		final String entityId,
		final String multiRefereceColumnName,
		final String referencedEntityId
	) {
		databaseWriter.deleteMultiReferenceEntry(tableName, entityId, multiRefereceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public final void deleteMultiValueEntry(
		final String tableName,
		final String entityId,
		final String multiFieldColumn,
		final String entry
	) {
		databaseWriter.deleteMultiValueEntry(tableName, entityId, multiFieldColumn, entry);
	}
	
	//method
	@Override
	public final void deleteEntity(final String tableName, final IEntityHeadDTO entity) {
		databaseWriter.deleteEntity(tableName, entity);
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
	public final void insertMultiReferenceEntry(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName,
		final String referencedEntityId
	) {
		databaseWriter.insertMultiReferenceEntry(tableName, entityId, multiReferenceColumnName, referencedEntityId);
	}
	
	//method
	@Override
	public final void insertMultiValueEntry(
		final String tableName,
		final String entityId,
		final String multiFieldColumn,
		final String entry
	) {
		databaseWriter.insertMultiValueEntry(tableName, entityId, multiFieldColumn, entry);
	}
	
	//method
	@Override
	public final void insertNewEntity(final String tableName, final INewEntityDTO newEntity) {
		databaseWriter.insertNewEntity(tableName, newEntity);
	}
	
	@Override
	public final IContainer<String> loadMultiReferenceEntries(
		final String tableName,
		final String entityId,
		final String multiReferenceColumnName
	) {
		return databaseReader.loadMultiReferenceEntries(tableName, entityId, multiReferenceColumnName);
	}
	
	//method
	@Override
	public final IContainer<Object> loadMultiValueEntries(
		final String tableName,
		final String entityId,
		final String multiFieldColumnName
	) {
		return databaseReader.loadMultiValueEntries(tableName, entityId, multiFieldColumnName);
	}
	
	//method	
	@Override
	public final IContainer<ILoadedEntityDTO> loadEntitiesOfTable(final String tableName) {
		return databaseReader.loadEntitiesOfTable(tableName);
	}
	
	//method
	@Override
	public final ILoadedEntityDTO loadEntity(final String tableName, final String id) {
		return databaseReader.loadEntity(tableName, id);
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
	public final boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
		return databaseReader.tableContainsEntityWithGivenId(tableName, id);
	}
	
	//method
	@Override
	public final void updateEntity(final String tableName, final IEntityUpdateDTO entityUpdate) {
		databaseWriter.updateEntity(tableName, entityUpdate);
	}
}
