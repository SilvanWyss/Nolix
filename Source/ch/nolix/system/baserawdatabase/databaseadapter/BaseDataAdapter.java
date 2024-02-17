//package declaration
package ch.nolix.system.baserawdatabase.databaseadapter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataReader;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataWriter;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedEntityDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public abstract class BaseDataAdapter implements IDataAdapter {

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //attribute
  private final IDataReader dataReader;

  //attribute
  private final IDataWriter dataWriter;

  //constructor
  protected BaseDataAdapter(final IDataReader dataReader, final IDataWriter dataWriter) {

    GlobalValidator.assertThat(dataReader).thatIsNamed(IDataReader.class).isNotNull();
    GlobalValidator.assertThat(dataWriter).thatIsNamed(IDataWriter.class).isNotNull();

    this.dataReader = dataReader;
    this.dataWriter = dataWriter;

    getStoredCloseController().createCloseDependencyTo(dataReader);
    getStoredCloseController().createCloseDependencyTo(dataWriter);
  }

  //method
  @Override
  public final void deleteEntity(final String tableName, final IEntityHeadDto entity) {
    dataWriter.deleteEntity(tableName, entity);
  }

  @Override
  public final void deleteMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    dataWriter.deleteMultiBackReferenceEntry(tableName, entityId, multiBackReferenceColumnId, backReferencedEntityId);
  }

  //method
  @Override
  public final void deleteMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    dataWriter.deleteMultiReferenceEntries(tableName, entityId, multiReferenceColumnName);
  }

  //method
  @Override
  public final void deleteMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiRefereceColumnName,
    final String referencedEntityId) {
    dataWriter.deleteMultiReferenceEntry(tableName, entityId, multiRefereceColumnName, referencedEntityId);
  }

  //method
  @Override
  public final void deleteMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiFieldColumn) {
    dataWriter.deleteMultiValueEntries(tableName, entityId, multiFieldColumn);
  }

  //method
  @Override
  public final void deleteMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiFieldColumn,
    final String entry) {
    dataWriter.deleteMultiValueEntry(tableName, entityId, multiFieldColumn, entry);
  }

  //method
  @Override
  public final void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    dataWriter.expectGivenSchemaTimestamp(schemaTimestamp);
  }

  //method
  @Override
  public final void expectTableContainsEntity(final String tableName, final String entityId) {
    dataWriter.expectTableContainsEntity(tableName, entityId);
  }

  //method
  @Override
  public final CloseController getStoredCloseController() {
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
    return dataReader.getSchemaTimestamp();
  }

  //method
  @Override
  public final boolean hasChanges() {
    return dataWriter.hasChanges();
  }

  //method
  @Override
  public final void insertMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    dataWriter.insertMultiReferenceEntry(tableName, entityId, multiBackReferenceColumnId, backReferencedEntityId);
  }

  //method
  @Override
  public final void insertMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName,
    final String referencedEntityId) {
    dataWriter.insertMultiReferenceEntry(tableName, entityId, multiReferenceColumnName, referencedEntityId);
  }

  //method
  @Override
  public final void insertMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiFieldColumn,
    final String entry) {
    dataWriter.insertMultiValueEntry(tableName, entityId, multiFieldColumn, entry);
  }

  //method
  @Override
  public final void insertEntity(final String tableName, final INewEntityDto newEntity) {
    dataWriter.insertEntity(tableName, newEntity);
  }

  @Override
  public final IContainer<String> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    return dataReader.loadMultiReferenceEntries(tableName, entityId, multiReferenceColumnName);
  }

  //method
  @Override
  public final IContainer<Object> loadMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiFieldColumnName) {
    return dataReader.loadMultiValueEntries(tableName, entityId, multiFieldColumnName);
  }

  //method
  @Override
  public final IContainer<ILoadedEntityDto> loadEntitiesOfTable(final String tableName) {
    return dataReader.loadEntitiesOfTable(tableName);
  }

  //method
  @Override
  public final ILoadedEntityDto loadEntity(final String tableName, final String id) {
    return dataReader.loadEntity(tableName, id);
  }

  //method
  @Override
  public final boolean multiValueIsEmpty(
    final String tableName,
    final String entityId,
    final String multiValueColumnId) {
    return dataReader.multiValueIsEmpty(tableName, entityId, multiValueColumnId);
  }

  //method
  @Override
  public final void noteClose() {
    //Does nothing.
  }

  //method
  @Override
  public final void reset() {
    dataWriter.reset();
  }

  //method
  @Override
  public final void saveChanges() {
    dataWriter.saveChanges();
  }

  //method
  @Override
  public final void setEntityAsUpdated(final String tableName, final IEntityHeadDto entity) {
    dataWriter.setEntityAsUpdated(tableName, entity);
  }

  //method
  @Override
  public final boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final String tableName,
    final String columnName,
    final String value) {
    return dataReader.tableContainsEntityWithGivenValueAtGivenColumn(tableName, columnName, value);
  }

  //method
  @Override
  public final boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
    return dataReader.tableContainsEntityWithGivenId(tableName, id);
  }

  //method
  @Override
  public final void updateEntity(final String tableName, final IEntityUpdateDto entityUpdate) {
    dataWriter.updateEntity(tableName, entityUpdate);
  }
}
