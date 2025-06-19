package ch.nolix.system.nodemiddata.datawriter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
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

  public DataWriter(final IMutableNode<?> nodeDatabase, final DatabaseViewDto databaseSchemaView) {

    Validator.assertThat(databaseSchemaView).thatIsNamed(DatabaseViewDto.class).isNotNull();

    this.databaseSchemaView = databaseSchemaView;
    this.executiveDataWriter = ExecutiveDataWriter.forNodeDatabase(nodeDatabase);
  }

  @Override
  public void clearMultiReference(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {

    final var tableView = getTableViewByTableName(tableName);

    final var multiReferenceColumnView = //
    TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableView, multiReferenceColumnName);

    final var multiReferencedColumnOneBasedOrdinalIndex = multiReferenceColumnView.oneBasedOrdinalIndex();

    executiveDataWriter.clearMultiReference(tableName, entityId, multiReferencedColumnOneBasedOrdinalIndex);
  }

  @Override
  public void clearMultiValue(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {

    final var tableInfo = getTableViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiValueColumnName);
    final var multiValueColumnOneBasedOrdinalIndex = columnView.oneBasedOrdinalIndex();

    executiveDataWriter.clearMultiValue(tableName, entityId, multiValueColumnOneBasedOrdinalIndex);
  }

  @Override
  public void deleteEntity(final String tableName, final EntityDeletionDto entity) {

    final var entityId = entity.id();
    final var entitySaveStamp = entity.saveStamp();

    executiveDataWriter.deleteEntity(tableName, entityId, entitySaveStamp);
  }

  @Override
  public void deleteMultiBackReferenceEntry(final MultiBackReferenceEntryDeletionDto multiBackReferenceEntry) {

    final var tableName = multiBackReferenceEntry.tableName();
    final var tableView = getTableViewByTableName(tableName);
    final var entityId = multiBackReferenceEntry.entityId();
    final var multiBackReferenceColumnName = multiBackReferenceEntry.multiBackReferenceColumnName();

    final var multiBackReferenceColumnView = //
    getColumnViewByTableNameAndColumnName(tableName, multiBackReferenceColumnName);

    final var multiBackReferenceColumnOneBasedOrdinalIndex = multiBackReferenceColumnView.oneBasedOrdinalIndex();
    final var backReferencedEntityId = multiBackReferenceEntry.backReferencedEntityId();

    executiveDataWriter.deleteMultiBackReferenceEntry(
      tableView,
      entityId,
      multiBackReferenceColumnOneBasedOrdinalIndex,
      backReferencedEntityId);
  }

  @Override
  public void deleteMultiReferenceEntry(final MultiReferenceEntryDeletionDto multiReferenceEntry) {

    final var tableName = multiReferenceEntry.tableName();
    final var tableView = getTableViewByTableName(tableName);
    final var entityId = multiReferenceEntry.entityId();
    final var multiReferenceColumnId = multiReferenceEntry.multiReferenceColumnId();
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnId(tableView, multiReferenceColumnId);
    final var multiReferencedColumnOneBasedOrdinalIndex = columnView.oneBasedOrdinalIndex();
    final var referencedEntityId = multiReferenceEntry.referencedEntityId();

    executiveDataWriter.deleteMultiReferenceEntry(
      tableName,
      entityId,
      multiReferencedColumnOneBasedOrdinalIndex,
      referencedEntityId);
  }

  @Override
  public void deleteMultiValueEntry(final MultiValueEntryDto multiValueEntry) {

    final var tableName = multiValueEntry.tableName();
    final var entityId = multiValueEntry.entityId();
    final var tableView = getTableViewByTableName(tableName);
    final var multiValueColumnName = multiValueEntry.multiValueColumnName();
    final var multiValueColumnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableView, multiValueColumnName);
    final var multiValueColumnOneBasedOrdinalIndex = multiValueColumnView.oneBasedOrdinalIndex();
    final var value = multiValueEntry.value();

    executiveDataWriter.deleteMultiValueEntry(tableName, entityId, multiValueColumnOneBasedOrdinalIndex, value);
  }

  @Override
  public void expectSchemaTimestamp(ITime schemaTimestamp) {
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
    executiveDataWriter.insertEntity(getTableViewByTableName(tableName), newEntity);
  }

  @Override
  public void insertMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {

    final var tableInfo = getTableViewByTableName(tableName);

    final var multiBackReferenceColumnInfo = //
    TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnId(tableInfo, multiBackReferenceColumnId);

    executiveDataWriter.insertMultiBackReferenceEntry(
      tableInfo,
      entityId,
      multiBackReferenceColumnInfo,
      backReferencedEntityId);
  }

  @Override
  public void insertMultiReferenceEntry(final MultiReferenceEntryDto multiReferenceEntry) {

    final var tableView = getTableViewByTableName(multiReferenceEntry.tableName());
    final var columndId = multiReferenceEntry.multiReferenceColumnId();
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnId(tableView, columndId);
    final var multiReferenceColumnOneBasedOrdinalIndex = columnView.oneBasedOrdinalIndex();

    executiveDataWriter.insertMultiReferenceEntry(multiReferenceEntry, multiReferenceColumnOneBasedOrdinalIndex);
  }

  @Override
  public void insertMultiValueEntry(
    final String tableName,
    final String entityId,
    final String multiValueColumnName,
    final String entry) {

    final var tableInfo = getTableViewByTableName(tableName);
    final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableInfo, multiValueColumnName);

    executiveDataWriter.insertMultiValueEntry(tableInfo, entityId, columnView, entry);
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
    executiveDataWriter.updateEntity(getTableViewByTableName(tableName), entityUpdate);
  }

  private ColumnViewDto getColumnViewByTableNameAndColumnName(final String tableName, final String columnName) {

    final var tableSchemaView = getTableViewByTableName(tableName);

    return TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableSchemaView, columnName);
  }

  private TableViewDto getTableViewByTableName(final String tableName) {

    //TODO: Create DatabaseViewSearcher
    return databaseSchemaView.tableSchemaViews().getStoredFirst(td -> td.name().equals(tableName));
  }
}
