package ch.nolix.system.baserawschema.databaseandschemaadapter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataAdapter;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.systemapi.rawdataapi.datadto.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityUpdateDto;
import ch.nolix.systemapi.rawschemaapi.flatschemadto.FlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.TableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public abstract class BaseDataAndSchemaAdapter implements IDataAndSchemaAdapter {

  private final CloseController closeController = CloseController.forElement(this);

  private final IDataAdapter dataAdapter;

  private final ISchemaReader schemaReader;

  protected BaseDataAndSchemaAdapter(
    final IDataAdapter dataAdapter,
    final ISchemaReader schemaReader) {

    GlobalValidator.assertThat(dataAdapter).thatIsNamed(IDataAdapter.class).isNotNull();
    GlobalValidator.assertThat(schemaReader).thatIsNamed(ISchemaReader.class).isNotNull();

    this.dataAdapter = dataAdapter;
    this.schemaReader = schemaReader;

    getStoredCloseController().createCloseDependencyTo(dataAdapter);
    getStoredCloseController().createCloseDependencyTo(schemaReader);
  }

  @Override
  public final boolean columnIsEmpty(final String tableName, final String columnName) {
    return schemaReader.columnIsEmpty(tableName, columnName);
  }

  @Override
  public final void deleteEntity(final String tableName, final EntityDeletionDto entity) {
    dataAdapter.deleteEntity(tableName, entity);
  }

  @Override
  public final void deleteMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    dataAdapter.deleteMultiBackReferenceEntry(tableName, entityId, multiBackReferenceColumnId, backReferencedEntityId);
  }

  @Override
  public final void deleteMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    dataAdapter.deleteMultiReferenceEntries(tableName, entityId, multiReferenceColumnName);
  }

  @Override
  public final void deleteMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiRefereceColumnName,
    final String referencedEntityId) {
    dataAdapter.deleteMultiReferenceEntry(tableName, entityId, multiRefereceColumnName, referencedEntityId);
  }

  @Override
  public final void deleteMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {
    dataAdapter.deleteMultiValueEntries(tableName, entityId, multiValueColumnName);
  }

  @Override
  public final void deleteMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {
    dataAdapter.deleteMultiValueEntry(tableName, entityId, multiValueColumnName, entry);
  }

  @Override
  public final void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    dataAdapter.expectGivenSchemaTimestamp(schemaTimestamp);
  }

  @Override
  public final void expectTableContainsEntity(final String tableName, final String entityId) {
    dataAdapter.expectTableContainsEntity(tableName, entityId);
  }

  @Override
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public final int getSaveCount() {
    return dataAdapter.getSaveCount();
  }

  @Override
  public final ITime getSchemaTimestamp() {
    return dataAdapter.getSchemaTimestamp();
  }

  @Override
  public final int getTableCount() {
    return schemaReader.getTableCount();
  }

  @Override
  public final boolean hasChanges() {
    return dataAdapter.hasChanges();
  }

  @Override
  public final void insertMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    dataAdapter.insertMultiBackReferenceEntry(tableName, entityId, multiBackReferenceColumnId, backReferencedEntityId);
  }

  @Override
  public final void insertMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId) {
    dataAdapter.insertMultiReferenceEntry(tableName, entityId, multiReferenceColumnId, referencedEntityId);
  }

  @Override
  public final void insertMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {
    dataAdapter.insertMultiValueEntry(tableName, entityId, multiValueColumnName, entry);
  }

  @Override
  public final void insertEntity(final String tableName, final EntityCreationDto newEntity) {
    dataAdapter.insertEntity(tableName, newEntity);
  }

  @Override
  public final IContainer<String> loadMultiBackReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnName) {
    return dataAdapter.loadMultiBackReferenceEntries(tableName, entityId, multiBackReferenceColumnName);
  }

  @Override
  public final IContainer<String> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    return dataAdapter.loadMultiReferenceEntries(tableName, entityId, multiReferenceColumnName);
  }

  @Override
  public final IContainer<Object> loadMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiFieldColumnName) {
    return dataAdapter.loadMultiValueEntries(tableName, entityId, multiFieldColumnName);
  }

  @Override
  public final IContainer<EntityLoadingDto> loadEntitiesOfTable(final String tableName) {
    return dataAdapter.loadEntitiesOfTable(tableName);
  }

  @Override
  public final IContainer<ColumnDto> loadColumnsByTableId(final String tableId) {
    return schemaReader.loadColumnsByTableId(tableId);
  }

  @Override
  public final IContainer<ColumnDto> loadColumnsByTableName(final String tableName) {
    return schemaReader.loadColumnsByTableName(tableName);
  }

  @Override
  public final FlatTableDto loadFlatTableById(String id) {
    return schemaReader.loadFlatTableById(id);
  }

  @Override
  public final FlatTableDto loadFlatTableByName(final String name) {
    return schemaReader.loadFlatTableByName(name);
  }

  @Override
  public final IContainer<FlatTableDto> loadFlatTables() {
    return schemaReader.loadFlatTables();
  }

  @Override
  public final EntityLoadingDto loadEntity(final String tableName, final String id) {
    return dataAdapter.loadEntity(tableName, id);
  }

  @Override
  public final ITime loadSchemaTimestamp() {
    return schemaReader.loadSchemaTimestamp();
  }

  @Override
  public final TableDto loadTableById(final String id) {
    return schemaReader.loadTableById(id);
  }

  @Override
  public final TableDto loadTableByName(final String name) {
    return schemaReader.loadTableByName(name);
  }

  @Override
  public final IContainer<TableDto> loadTables() {
    return schemaReader.loadTables();
  }

  @Override
  public final void noteClose() {
  }

  @Override
  public final void reset() {
    dataAdapter.reset();
  }

  @Override
  public final void saveChanges() {
    dataAdapter.saveChanges();
  }

  @Override
  public final boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final String tableName,
    final String columnName,
    final String value) {
    return dataAdapter.tableContainsEntityWithGivenValueAtGivenColumn(tableName, columnName, value);
  }

  @Override
  public final boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final String tableName,
    final String columnName,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    dataAdapter.tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
      tableName,
      columnName,
      value,
      entitiesToIgnoreIds);
  }

  @Override
  public final boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
    return dataAdapter.tableContainsEntityWithGivenId(tableName, id);
  }

  @Override
  public final void updateEntity(final String tableName, final EntityUpdateDto entityUpdate) {
    dataAdapter.updateEntity(tableName, entityUpdate);
  }
}
