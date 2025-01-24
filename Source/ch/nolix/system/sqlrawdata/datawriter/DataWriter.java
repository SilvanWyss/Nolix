package ch.nolix.system.sqlrawdata.datawriter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.rawdata.schemaviewdtosearcher.TableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataWriter;
import ch.nolix.systemapi.rawdataapi.model.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.model.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.model.EntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableViewDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DataWriter implements IDataWriter {

  private static final ITableViewDtoSearcher TABLE_VIEW_DTO_SEARCHER = new TableViewDtoSearcher();

  private final ICloseController closeController = CloseController.forElement(this);

  private final InternalDataWriter internalDataWriter;

  private final IContainer<TableViewDto> tableViews;

  private DataWriter(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final IContainer<TableViewDto> tableViews) {

    GlobalValidator.assertThat(tableViews).thatIsNamed("table definitions").isNotNull();

    internalDataWriter = new InternalDataWriter(databaseName, sqlConnection);
    this.tableViews = tableViews;

    createCloseDependencyTo(sqlConnection);
  }

  public static DataWriter forDatabaseNameAndSqlConnectionAndTableViews(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final IContainer<TableViewDto> tableViews) {
    return new DataWriter(databaseName, sqlConnection, tableViews);
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

  private ColumnViewDto getColumnDefinitionByTableNameAndColumnName(
    final String tableName,
    final String columnName) {

    final var tableView = getTableDefinitionByTableName(tableName);

    return TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableView, columnName);
  }

  private TableViewDto getTableDefinitionByTableName(final String tableName) {
    return tableViews.getStoredFirst(td -> td.name().equals(tableName));
  }
}
