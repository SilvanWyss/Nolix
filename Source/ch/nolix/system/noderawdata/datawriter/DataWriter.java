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
import ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.DatabaseSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DataWriter implements IDataWriter {

  private static final ITableViewDtoSearcher TABLE_VIEW_DTO_SEARCHER = new TableViewDtoSearcher();

  private final ICloseController closeController = CloseController.forElement(this);

  private final DatabaseSchemaViewDto databaseSchemaView;

  private final InternalDataWriter internalDataWriter;

  public DataWriter(final IMutableNode<?> nodeDatabase, final DatabaseSchemaViewDto databaseSchemaView) {

    GlobalValidator.assertThat(databaseSchemaView).thatIsNamed(DatabaseSchemaViewDto.class).isNotNull();

    this.databaseSchemaView = databaseSchemaView;
    this.internalDataWriter = new InternalDataWriter(nodeDatabase);
  }

  @Override
  public void deleteEntity(final String tableName, final EntityDeletionDto entity) {
    internalDataWriter.deleteEntityFromTable(tableName, entity);
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

    internalDataWriter.deleteMultiBackReferenceEntry(
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

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiReferenceColumnName);

    internalDataWriter.deleteEntriesFromMultiReference(tableInfo, entityId, columnView);
  }

  @Override
  public void deleteMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiRefereceColumnName,
    final String referencedEntityId) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiRefereceColumnName);

    internalDataWriter.deleteEntryFromMultiReference(tableInfo, entityId, columnView, referencedEntityId);
  }

  @Override
  public void deleteMultiValueEntries(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiValueColumnName);

    internalDataWriter.deleteEntriesFromMultiValue(tableInfo, entityId, columnView);
  }

  @Override
  public void deleteMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiValueColumnName);

    internalDataWriter.deleteEntryFromMultiValue(tableInfo, entityId, columnView, entry);
  }

  @Override
  public void expectGivenSchemaTimestamp(ITime schemaTimestamp) {
    internalDataWriter.expectGivenSchemaTimestamp(schemaTimestamp);
  }

  @Override
  public void expectTableContainsEntity(final String tableName, final String entityId) {
    internalDataWriter.expectTableContainsEntity(tableName, entityId);
  }

  @Override
  public int getSaveCount() {
    return internalDataWriter.getSaveCount();
  }

  @Override
  public boolean hasChanges() {
    return internalDataWriter.hasUpdates();
  }

  @Override
  public void insertEntity(final String tableName, final EntityCreationDto newEntity) {
    internalDataWriter.insertEntityIntoTable(getTableSchemaViewByTableName(tableName), newEntity);
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

    internalDataWriter.insertEntryIntoMultiBackReference(
      tableInfo,
      entityId,
      multiBackReferenceColumnInfo,
      backReferencedEntityId);
  }

  @Override
  public void insertMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnId(tableInfo, multiReferenceColumnId);

    internalDataWriter.insertEntryIntoMultiReference(
      tableInfo,
      entityId,
      columnView,
      referencedEntityId);
  }

  @Override
  public void insertMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {

    final var tableInfo = getTableSchemaViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiValueColumnName);

    internalDataWriter.insertEntryIntoMultiValue(tableInfo, entityId, columnView, entry);
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
    internalDataWriter.reset();
  }

  @Override
  public void saveChanges() {
    internalDataWriter.saveChangesAndReset();
  }

  @Override
  public void updateEntity(final String tableName, final EntityUpdateDto entityUpdate) {
    internalDataWriter.updateEntityOnTable(getTableSchemaViewByTableName(tableName), entityUpdate);
  }

  private TableSchemaViewDto getTableSchemaViewByTableName(final String tableName) {
    return databaseSchemaView.tableSchemaViews().getStoredFirst(td -> td.name().equals(tableName));
  }
}
