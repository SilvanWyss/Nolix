package ch.nolix.system.sqlmiddata.datawriter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.middata.midschemaviewsearcher.TableViewDtoSearcher;
import ch.nolix.systemapi.middataapi.adapterapi.IDataWriter;
import ch.nolix.systemapi.middataapi.midschemaview.ColumnViewDto;
import ch.nolix.systemapi.middataapi.midschemaview.DatabaseViewDto;
import ch.nolix.systemapi.middataapi.midschemaview.TableViewDto;
import ch.nolix.systemapi.middataapi.midschemaviewsearcherapi.ITableViewDtoSearcher;
import ch.nolix.systemapi.middataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiBackReferenceEntryDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiValueEntryDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DataWriter implements IDataWriter {

  private static final ITableViewDtoSearcher TABLE_VIEW_DTO_SEARCHER = new TableViewDtoSearcher();

  private final ICloseController closeController = CloseController.forElement(this);

  private final DatabaseViewDto databaseSchemaView;

  private final ExecutiveDataWriter executiveDataWriter;

  private DataWriter(
    final String databaseName,
    final DatabaseViewDto databaseSchemaView,
    final ISqlConnection sqlConnection) {

    Validator.assertThat(databaseSchemaView).thatIsNamed(DatabaseViewDto.class).isNotNull();

    this.databaseSchemaView = databaseSchemaView;
    executiveDataWriter = new ExecutiveDataWriter(databaseName, sqlConnection);

    createCloseDependencyTo(sqlConnection);
  }

  public static DataWriter forDatabaseNameAndDatabaseSchemaViewAndSqlConnection(
    final String databaseName,
    final DatabaseViewDto databaseSchemaView,
    final ISqlConnection sqlConnection) {
    return new DataWriter(databaseName, databaseSchemaView, sqlConnection);
  }

  @Override
  public void clearMultiReference(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {
    executiveDataWriter.deleteEntriesFromMultiReference(
      entityId,
      getColumnViewByTableNameAndColumnName(tableName, multiReferenceColumnName).id());
  }

  @Override
  public void clearMultiValue(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {
    executiveDataWriter.deleteEntriesFromMultiValue(
      entityId,
      getColumnViewByTableNameAndColumnName(tableName, multiValueColumnName).id());
  }

  @Override
  public void deleteEntity(final String tableName, final EntityDeletionDto entity) {
    executiveDataWriter.deleteEntity(tableName, entity);
  }

  @Override
  public void deleteMultiBackReferenceEntry(final MultiBackReferenceEntryDeletionDto multiBackReferenceEntry) {

    final var tableName = multiBackReferenceEntry.tableName();
    final var entityId = multiBackReferenceEntry.entityId();
    final var multiBackReferenceColumnName = multiBackReferenceEntry.multiBackReferenceColumnName();

    final var multiBackReferenceColumnView = //
    getColumnViewByTableNameAndColumnName(tableName, multiBackReferenceColumnName);

    final var multiBackReferenceColumnId = multiBackReferenceColumnView.id();
    final var backReferencedEntityId = multiBackReferenceEntry.backReferencedEntityId();

    executiveDataWriter.deleteMultiBackReferenceEntry(entityId, multiBackReferenceColumnId, backReferencedEntityId);
  }

  @Override
  public void deleteMultiReferenceEntry(final MultiReferenceEntryDeletionDto multiReferenceEntry) {

    final var tableName = multiReferenceEntry.tableName();
    final var entityId = multiReferenceEntry.entityId();
    final var multiReferenceColumnName = multiReferenceEntry.multiReferenceColumnName();
    final var multiReferenceColumnView = getColumnViewByTableNameAndColumnName(tableName, multiReferenceColumnName);
    final var multiReferenceColumnId = multiReferenceColumnView.id();
    final var referencedEntityId = multiReferenceEntry.referencedEntityId();

    executiveDataWriter.deleteEntryFromMultiReference(entityId, multiReferenceColumnId, referencedEntityId);
  }

  @Override
  public void deleteMultiValueEntry(final MultiValueEntryDto multiValueEntry) {

    final var tableName = multiValueEntry.tableName();
    final var entityId = multiValueEntry.entityId();
    final var multiValueColumnName = multiValueEntry.multiValueColumnName();
    final var multiValueColumnView = getColumnViewByTableNameAndColumnName(tableName, multiValueColumnName);
    final var multiValueColumnId = multiValueColumnView.id();
    final var value = multiValueEntry.value();

    executiveDataWriter.deleteMultiValueEntry(entityId, multiValueColumnId, value);
  }

  @Override
  public void expectSchemaTimestamp(final ITime schemaTimestamp) {
    executiveDataWriter.expectGivenSchemaTimestamp(schemaTimestamp);
  }

  @Override
  public void expectTableContainsEntity(final String tableName, final String entityId) {
    executiveDataWriter.expectTableContainsEntity(tableName, entityId);
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public int getSaveCount() {
    return executiveDataWriter.getSaveCount();
  }

  @Override
  public boolean hasChanges() {
    return executiveDataWriter.hasChanges();
  }

  @Override
  public void insertEntity(final String tableName, final EntityCreationDto newEntity) {
    executiveDataWriter.insertEntity(tableName, newEntity);
  }

  @Override
  public void insertMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    executiveDataWriter.insertEntryIntoMultiBackReference(entityId, multiBackReferenceColumnId, backReferencedEntityId);
  }

  @Override
  public void insertMultiReferenceEntry(final MultiReferenceEntryDto multiReferenceEntry) {

    final var entityId = multiReferenceEntry.entityid();
    final var columnId = multiReferenceEntry.multiReferenceColumnId();
    final var referencedEntityId = multiReferenceEntry.referencedEntityId();
    final var referencedEntityTableId = multiReferenceEntry.referencedEntityId();

    executiveDataWriter.insertMultiReferenceEntry(entityId, columnId, referencedEntityId, referencedEntityTableId);
  }

  @Override
  public void insertMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {
    executiveDataWriter.insertEntryIntoMultiValue(
      entityId,
      getColumnViewByTableNameAndColumnName(tableName, multiValueColumnName).id(),
      entry);
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  @Override
  public void reset() {
    executiveDataWriter.reset();
  }

  @Override
  public void saveChanges() {
    executiveDataWriter.saveChangesAndReset();
  }

  @Override
  public void updateEntity(final String tableName, final EntityUpdateDto entityUpdate) {
    executiveDataWriter.updateEntityOnTable(tableName, entityUpdate);
  }

  private ColumnViewDto getColumnViewByTableNameAndColumnName(final String tableName, final String columnName) {

    final var tableSchemaView = getTableViewByTableName(tableName);

    return TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableSchemaView, columnName);
  }

  private TableViewDto getTableViewByTableName(final String tableName) {

    //TODO: Create DatabaseViewSearcher
    return databaseSchemaView.tableSchemaViews().getStoredFirst(t -> t.name().equals(tableName));
  }
}
