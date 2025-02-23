package ch.nolix.system.noderawdata.datawriter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.system.rawdata.schemaviewdtosearcher.TableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataWriter;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.DatabaseSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DataWriter implements IDataWriter {

  private static final ITableViewDtoSearcher TABLE_VIEW_DTO_SEARCHER = new TableViewDtoSearcher();

  private final ICloseController closeController = CloseController.forElement(this);

  private final DatabaseSchemaViewDto databaseSchemaView;

  private final ExecutiveDataWriter executiveDataWriter;

  public DataWriter(final IMutableNode<?> nodeDatabase, final DatabaseSchemaViewDto databaseSchemaView) {

    GlobalValidator.assertThat(databaseSchemaView).thatIsNamed(DatabaseSchemaViewDto.class).isNotNull();

    this.databaseSchemaView = databaseSchemaView;
    this.executiveDataWriter = ExecutiveDataWriter.forNodeDatabase(nodeDatabase);
  }

  @Override
  public void deleteEntity(final String tableName, final EntityDeletionDto entity) {
    executiveDataWriter.deleteEntityFromTable(tableName, entity);
  }

  @Override
  public void deleteMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var multiBackReferenceColumnInfo = //
    TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnId(tableInfo, multiBackReferenceColumnId);

    executiveDataWriter.deleteMultiBackReferenceEntry(
      tableInfo,
      entityId,
      multiBackReferenceColumnInfo,
      backReferencedEntityId);
  }

  @Override
  public void deleteMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {

    final var tableView = getTableSchemaViewByTableName(tableName);

    final var multiReferenceColumnView = //
    TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableView, multiReferenceColumnName);

    final var multiReferencedColumnOneBasedOrdinalIndex = multiReferenceColumnView.oneBasedOrdinalIndex();

    executiveDataWriter.deleteEntriesFromMultiReference(tableName, entityId, multiReferencedColumnOneBasedOrdinalIndex);
  }

  @Override
  public void deleteMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiRefereceColumnName,
    final String referencedEntityId) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiRefereceColumnName);

    executiveDataWriter.deleteEntryFromMultiReference(tableInfo, entityId, columnView, referencedEntityId);
  }

  @Override
  public void deleteMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiValueColumnName);

    executiveDataWriter.deleteEntriesFromMultiValue(tableInfo, entityId, columnView);
  }

  @Override
  public void deleteMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiValueColumnName);

    executiveDataWriter.deleteEntryFromMultiValue(tableInfo, entityId, columnView, entry);
  }

  @Override
  public void expectGivenSchemaTimestamp(ITime schemaTimestamp) {
    executiveDataWriter.expectSchemaTimestamp(schemaTimestamp);
  }

  @Override
  public void expectTableContainsEntity(final String tableName, final String entityId) {
    executiveDataWriter.expectTableContainsEntity(tableName, entityId);
  }

  @Override
  public int getSaveCount() {
    return executiveDataWriter.getSaveCount();
  }

  @Override
  public boolean hasChanges() {
    return executiveDataWriter.hasUpdates();
  }

  @Override
  public void insertEntity(final String tableName, final EntityCreationDto newEntity) {
    executiveDataWriter.insertEntityIntoTable(getTableSchemaViewByTableName(tableName), newEntity);
  }

  @Override
  public void insertMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);

    final var multiBackReferenceColumnInfo = //
    TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnId(tableInfo, multiBackReferenceColumnId);

    executiveDataWriter.insertEntryIntoMultiBackReference(
      tableInfo,
      entityId,
      multiBackReferenceColumnInfo,
      backReferencedEntityId);
  }

  @Override
  public void insertMultiReferenceEntry(final MultiReferenceEntryDto multiReferenceEntry) {

    final var tableView = getTableSchemaViewByTableName(multiReferenceEntry.tableName());
    final var columndId = multiReferenceEntry.multiReferenceColumnId();
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnId(tableView, columndId);
    final var multiReferenceColumnOneBasedOrdinalIndex = columnView.oneBasedOrdinalIndex();

    executiveDataWriter.insertEntryIntoMultiReference(multiReferenceEntry, multiReferenceColumnOneBasedOrdinalIndex);
  }

  @Override
  public void insertMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiValueColumnName);

    executiveDataWriter.insertEntryIntoMultiValue(tableInfo, entityId, columnView, entry);
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
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
    executiveDataWriter.updateEntityOnTable(getTableSchemaViewByTableName(tableName), entityUpdate);
  }

  private TableSchemaViewDto getTableSchemaViewByTableName(final String tableName) {
    return databaseSchemaView.tableSchemaViews().getStoredFirst(td -> td.name().equals(tableName));
  }
}
