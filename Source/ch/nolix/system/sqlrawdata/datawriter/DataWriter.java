package ch.nolix.system.sqlrawdata.datawriter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.rawdata.schemaviewdtosearcher.TableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataWriter;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.DatabaseSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DataWriter implements IDataWriter {

  private static final ITableViewDtoSearcher TABLE_VIEW_DTO_SEARCHER = new TableViewDtoSearcher();

  private final ICloseController closeController = CloseController.forElement(this);

  private final DatabaseSchemaViewDto databaseSchemaView;

  private final InternalDataWriter internalDataWriter;

  private DataWriter(
    final String databaseName,
    final DatabaseSchemaViewDto databaseSchemaView,
    final ISqlConnection sqlConnection) {

    GlobalValidator.assertThat(databaseSchemaView).thatIsNamed(DatabaseSchemaViewDto.class).isNotNull();

    this.databaseSchemaView = databaseSchemaView;
    internalDataWriter = new InternalDataWriter(databaseName, sqlConnection);

    createCloseDependencyTo(sqlConnection);
  }

  public static DataWriter forDatabaseNameAndDatabaseSchemaViewAndSqlConnection(
    final String databaseName,
    final DatabaseSchemaViewDto databaseSchemaView,
    final ISqlConnection sqlConnection) {
    return new DataWriter(databaseName, databaseSchemaView, sqlConnection);
  }

  @Override
  public void deleteEntity(final String tableName, final EntityDeletionDto entity) {
    internalDataWriter.deleteEntity(tableName, entity);
  }

  @Override
  public void deleteMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    internalDataWriter.deleteMultiBackReferenceEntry(entityId, multiBackReferenceColumnId, backReferencedEntityId);
  }

  @Override
  public void deleteMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    internalDataWriter.deleteEntriesFromMultiReference(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiReferenceColumnName).id());
  }

  @Override
  public void deleteMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiRefereceColumnName,
    final String referencedEntityId) {
    internalDataWriter.deleteEntryFromMultiReference(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiRefereceColumnName).id(),
      referencedEntityId);
  }

  @Override
  public void deleteMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {
    internalDataWriter.deleteEntriesFromMultiValue(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).id());
  }

  @Override
  public void deleteMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {
    internalDataWriter.deleteEntryFromMultiValue(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).id(),
      entry);
  }

  @Override
  public void expectGivenSchemaTimestamp(final ITime schemaTimestamp) {
    internalDataWriter.expectGivenSchemaTimestamp(schemaTimestamp);
  }

  @Override
  public void expectTableContainsEntity(final String tableName, final String entityId) {
    internalDataWriter.expectTableContainsEntity(tableName, entityId);
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public int getSaveCount() {
    return internalDataWriter.getSaveCount();
  }

  @Override
  public boolean hasChanges() {
    return internalDataWriter.hasChanges();
  }

  @Override
  public void insertEntity(final String tableName, final EntityCreationDto newEntity) {
    internalDataWriter.insertEntity(tableName, newEntity);
  }

  @Override
  public void insertMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    internalDataWriter.insertEntryIntoMultiBackReference(entityId, multiBackReferenceColumnId, backReferencedEntityId);
  }

  @Override
  public void insertMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName,
    final String referencedEntityId) {
    internalDataWriter.insertEntryIntoMultiReference(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiReferenceColumnName).id(),
      referencedEntityId);
  }

  @Override
  public void insertMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {
    internalDataWriter.insertEntryIntoMultiValue(
      entityId,
      getColumnDefinitionByTableNameAndColumnName(tableName, multiValueColumnName).id(),
      entry);
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  @Override
  public void reset() {
    internalDataWriter.reset();
  }

  @Override
  public void saveChanges() {
    internalDataWriter.saveChangesAndReset();
  }

  @Override
  public void updateEntity(final String tableName, final EntityUpdateDto entityUpdate) {
    internalDataWriter.updateEntityOnTable(tableName, entityUpdate);
  }

  private ColumnSchemaViewDto getColumnDefinitionByTableNameAndColumnName(
    final String tableName,
    final String columnName) {

    final var tableSchemaView = getTableSchmeaViewByTableName(tableName);

    return TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableSchemaView, columnName);
  }

  private TableSchemaViewDto getTableSchmeaViewByTableName(final String tableName) {
    return databaseSchemaView.tableSchemaViews().getStoredFirst(t -> t.name().equals(tableName));
  }
}
