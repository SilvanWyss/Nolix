package ch.nolix.system.nodemiddata.datawriter;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.system.midschemaview.modelsearcher.DatabaseViewSearcher;
import ch.nolix.system.nodemiddata.nodemapper.EntityIndexNodeMapper;
import ch.nolix.system.nodemiddata.nodemapper.EntityNodeMapper;
import ch.nolix.systemapi.middataapi.adapterapi.IDataWriter;
import ch.nolix.systemapi.middataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiBackReferenceEntryDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiValueEntryDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.ColumnViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.DatabaseViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelsearcherapi.IDatabaseViewSearcher;
import ch.nolix.systemapi.nodemiddataapi.nodemapperapi.IEntityIndexNodeMapper;
import ch.nolix.systemapi.nodemiddataapi.nodemapperapi.IEntityNodeMapper;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DataWriter implements IDataWriter {

  public static final int INITIAL_ENTITY_SAVE_STAMP = 0;

  private static final IDatabaseViewSearcher DATABASE_VIEW_SEARCHER = new DatabaseViewSearcher();

  private static final IEntityNodeMapper ENTITY_NODE_MAPPER = new EntityNodeMapper();

  private static final IEntityIndexNodeMapper ENTITY_INDEXES_NODE_MAPPER = new EntityIndexNodeMapper();

  private final ICloseController closeController = CloseController.forElement(this);

  private final DatabaseViewDto databaseView;

  private final ExecutiveDataWriter executiveDataWriter;

  private DataWriter(final IMutableNode<?> nodeDatabase, final DatabaseViewDto databaseView) {

    Validator.assertThat(databaseView).thatIsNamed("database view").isNotNull();

    this.databaseView = databaseView;
    this.executiveDataWriter = ExecutiveDataWriter.forNodeDatabase(nodeDatabase);
  }

  public static DataWriter forNodeDatabaseAndDatabaseView(
    final IMutableNode<?> nodeDatabase,
    final DatabaseViewDto databaseView) {
    return new DataWriter(nodeDatabase, databaseView);
  }

  @Override
  public void clearMultiReference(
    final String tableName,
    final String entityId,
    final String multiReferenceColumnName) {

    final var multiReferenceColumnView = getColumnViewByTableNameAndColumnName(tableName, multiReferenceColumnName);
    final var multiReferencedColumnOneBasedOrdinalIndex = multiReferenceColumnView.oneBasedOrdinalIndex();

    executiveDataWriter.clearMultiReference(tableName, entityId, multiReferencedColumnOneBasedOrdinalIndex);
  }

  @Override
  public void clearMultiValue(
    final String tableName,
    final String entityId,
    final String multiValueColumnName) {

    final var multiValueColumnView = getColumnViewByTableNameAndColumnName(tableName, multiValueColumnName);
    final var multiValueColumnOneBasedOrdinalIndex = multiValueColumnView.oneBasedOrdinalIndex();

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
    final var entityId = multiBackReferenceEntry.entityId();
    final var multiBackReferenceColumnName = multiBackReferenceEntry.multiBackReferenceColumnName();

    final var multiBackReferenceColumnView = //
    getColumnViewByTableNameAndColumnName(tableName, multiBackReferenceColumnName);

    final var multiBackReferenceColumnOneBasedOrdinalIndex = multiBackReferenceColumnView.oneBasedOrdinalIndex();
    final var backReferencedEntityId = multiBackReferenceEntry.backReferencedEntityId();

    executiveDataWriter.deleteMultiBackReferenceEntry(
      tableName,
      entityId,
      multiBackReferenceColumnOneBasedOrdinalIndex,
      backReferencedEntityId);
  }

  @Override
  public void deleteMultiReferenceEntry(final MultiReferenceEntryDeletionDto multiReferenceEntry) {

    final var tableName = multiReferenceEntry.tableName();
    final var entityId = multiReferenceEntry.entityId();
    final var multiReferenceColumnName = multiReferenceEntry.multiReferenceColumnName();
    final var multiReferenceColumnNameView = getColumnViewByTableNameAndColumnName(tableName, multiReferenceColumnName);
    final var multiReferencedColumnOneBasedOrdinalIndex = multiReferenceColumnNameView.oneBasedOrdinalIndex();
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
    final var multiValueColumnName = multiValueEntry.multiValueColumnName();
    final var multiValueColumnView = getColumnViewByTableNameAndColumnName(tableName, multiValueColumnName);
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
  public void insertEntity(final String tableName, final EntityCreationDto entity) {

    final var tableView = getTableViewByTableName(tableName);
    final var tableId = tableView.id();
    final var entityId = entity.id();
    final var entityIndexNode = ENTITY_INDEXES_NODE_MAPPER.mapEntityCreationDtoToEntityIndexNode(entity, tableId);
    final var saveStamp = INITIAL_ENTITY_SAVE_STAMP;
    final var entityNode = ENTITY_NODE_MAPPER.mapEntityCreationDtoToEntityNode(entity, tableView, saveStamp);

    executiveDataWriter.insertEntity(tableName, entityId, entityIndexNode, entityNode);
  }

  @Override
  public void insertMultiBackReferenceEntry(final MultiBackReferenceEntryDto multiBackReferenceEntry) {

    final var tableName = multiBackReferenceEntry.tableName();
    final var entityId = multiBackReferenceEntry.entityId();
    final var multiBackReferenceColumnName = multiBackReferenceEntry.multiBackReferenceColumnName();

    final var multiBackReferenceColumnView = //
    getColumnViewByTableNameAndColumnName(tableName, multiBackReferenceColumnName);

    final var multiBackReferenceColumnOneBasedOrdinalIndex = multiBackReferenceColumnView.oneBasedOrdinalIndex();
    final var backReferencedEntityId = multiBackReferenceEntry.backReferencedEntityId();
    final var backReferencedEntityTableName = multiBackReferenceEntry.backReferencedEntityTableName();
    final var backReferencedEntityTableView = getTableViewByTableName(backReferencedEntityTableName);
    final var backReferencedEntityTableId = backReferencedEntityTableView.id();

    executiveDataWriter.insertMultiBackReferenceEntry(
      tableName,
      entityId,
      multiBackReferenceColumnOneBasedOrdinalIndex,
      backReferencedEntityId,
      backReferencedEntityTableId);
  }

  @Override
  public void insertMultiReferenceEntry(final MultiReferenceEntryDto multiReferenceEntry) {

    final var tableName = multiReferenceEntry.tableName();
    final var entityId = multiReferenceEntry.entityid();
    final var multiReferenceColumnName = multiReferenceEntry.multiReferenceColumnName();
    final var multiReferenceColumnView = getColumnViewByTableNameAndColumnName(tableName, multiReferenceColumnName);
    final var multiReferenceColumnOneBasedOrdinalIndex = multiReferenceColumnView.oneBasedOrdinalIndex();

    //TODO: Create MultiReferenceEntryNodeMapper
    final var multiReferenceEntryNode = Node.withHeader(multiReferenceEntry.referencedEntityId());

    executiveDataWriter.insertMultiReferenceEntry(
      tableName,
      entityId,
      multiReferenceColumnOneBasedOrdinalIndex,
      multiReferenceEntryNode);
  }

  @Override
  public void insertMultiValueEntry(final MultiValueEntryDto multiValueEntry) {

    final var tableName = multiValueEntry.tableName();
    final var entityId = multiValueEntry.entityId();
    final var multiValueColumnName = multiValueEntry.multiValueColumnName();
    final var multiValueColumnView = getColumnViewByTableNameAndColumnName(tableName, multiValueColumnName);
    final var multiValueColumnOneBasedOrdinalIndex = multiValueColumnView.oneBasedOrdinalIndex();
    final var value = multiValueEntry.value();

    executiveDataWriter.insertMultiValueEntry(tableName, entityId, multiValueColumnOneBasedOrdinalIndex, value);
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

    final var tableView = getTableViewByTableName(tableName);

    executiveDataWriter.updateEntity(entityUpdate, tableView);
  }

  private ColumnViewDto getColumnViewByTableNameAndColumnName(final String tableName, final String columnName) {
    return DATABASE_VIEW_SEARCHER.getColumnViewByTableNameAndColumnName(databaseView, tableName, columnName);
  }

  private TableViewDto getTableViewByTableName(final String tableName) {
    return DATABASE_VIEW_SEARCHER.getTableViewByTableName(databaseView, tableName);
  }
}
