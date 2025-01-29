package ch.nolix.system.noderawdata.datawriter;

import java.util.function.Consumer;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.process.UpdaterCollector;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.programcontrolapi.processapi.IUpdaterCollector;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableViewDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

final class InternalDataWriter {

  private static final DatabaseUpdater DATABASE_UPDATER = new DatabaseUpdater();

  private int saveCount;

  private final IMutableNode<?> nodeDatabase;

  private final IUpdaterCollector<IMutableNode<?>> updaterCollector = new UpdaterCollector<>();

  public InternalDataWriter(final IMutableNode<?> nodeDatabase) {

    GlobalValidator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    this.nodeDatabase = nodeDatabase;
  }

  public void deleteEntriesFromMultiReference(
    final TableViewDto tableView,
    final String entityId,
    final ColumnViewDto multiReferenceColumnInfo) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATABASE_UPDATER.deleteEntriesFromMultiReference(d, tableView, entityId, multiReferenceColumnInfo);

    addUpdateAction(updateAction);
  }

  public void deleteEntriesFromMultiValue(
    final TableViewDto tableView,
    final String entityId,
    final ColumnViewDto multiValueColumnInfo) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATABASE_UPDATER.deleteEntriesFromMultiValue(d, tableView, entityId, multiValueColumnInfo);

    addUpdateAction(updateAction);
  }

  public void deleteEntryFromMultiReference(
    final TableViewDto tableView,
    final String entityId,
    final ColumnViewDto multiReferenceColumnInfo,
    final String referencedEntityId) {

    final Consumer<IMutableNode<?>> updateAction = d -> DATABASE_UPDATER.deleteEntryFromMultiReference(
      d,
      tableView,
      entityId,
      multiReferenceColumnInfo,
      referencedEntityId);

    addUpdateAction(updateAction);
  }

  public void deleteEntryFromMultiValue(
    final TableViewDto tableView,
    final String entityId,
    final ColumnViewDto multiValueColumnInfo,
    final String entry) {

    final Consumer<IMutableNode<?>> updateAction = d -> DATABASE_UPDATER.deleteEntryFromMultiValue(d, tableView,
      entityId, multiValueColumnInfo, entry);

    addUpdateAction(updateAction);
  }

  public void deleteEntityFromTable(final String tableName, final EntityDeletionDto entity) {
    addUpdateAction(d -> DATABASE_UPDATER.deleteEntityFromTable(d, tableName, entity));
  }

  public void deleteMultiBackReferenceEntry(
    final TableViewDto tableView,
    final String entityId,
    final ColumnViewDto multiBackReferenceColumnInfo,
    final String backReferencedEntityId) {

    final Consumer<IMutableNode<?>> updateAction = d -> DATABASE_UPDATER.deleteMultiBackReferenceEntry(
      d,
      tableView,
      entityId,
      multiBackReferenceColumnInfo,
      backReferencedEntityId);

    addUpdateAction(updateAction);
  }

  public void expectGivenSchemaTimestamp(ITime schemaTimestamp) {

    final Consumer<IMutableNode<?>> updateAction = d -> DATABASE_UPDATER.expectGivenSchemaTimestamp(d, schemaTimestamp);

    addUpdateAction(updateAction);
  }

  public void expectTableContainsEntity(final String tableName, final String entityId) {

    final Consumer<IMutableNode<?>> updateAction = d -> DATABASE_UPDATER.expectTableContainsEntity(d, tableName,
      entityId);

    addUpdateAction(updateAction);
  }

  public int getSaveCount() {
    return saveCount;
  }

  public boolean hasUpdates() {
    return updaterCollector.containsAny();
  }

  public void insertEntryIntoMultiBackReference(
    final TableViewDto tableView,
    final String entityId,
    final ColumnViewDto multiBackReferenceColumnInfo,
    final String backReferencedEntityId) {

    final Consumer<IMutableNode<?>> updateAction = d -> DATABASE_UPDATER.insertEntryIntoMultiBackReference(
      d,
      tableView,
      entityId,
      multiBackReferenceColumnInfo,
      backReferencedEntityId);

    addUpdateAction(updateAction);
  }

  public void insertEntryIntoMultiReference(
    final TableViewDto tableView,
    final String entityId,
    final ColumnViewDto multiReferenceColumnInfo,
    final String referencedEntityId) {

    final Consumer<IMutableNode<?>> updateAction = d -> DATABASE_UPDATER.insertEntryIntoMultiReference(
      d,
      tableView,
      entityId,
      multiReferenceColumnInfo,
      referencedEntityId);

    addUpdateAction(updateAction);
  }

  public void insertEntryIntoMultiValue(
    final TableViewDto tableView,
    final String entityId,
    final ColumnViewDto multiValueColumnInfo,
    final String entry) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATABASE_UPDATER.insertEntryIntoMultiValue(d, tableView, entityId, multiValueColumnInfo, entry);

    addUpdateAction(updateAction);
  }

  public void insertEntityIntoTable(final TableViewDto tableView, final EntityCreationDto newEntity) {

    final Consumer<IMutableNode<?>> updateAction = d -> DATABASE_UPDATER.insertEntityIntoTable(d, tableView, newEntity);

    addUpdateAction(updateAction);
  }

  public void reset() {
    updaterCollector.clear();
  }

  public void saveChangesAndReset() {
    try {
      nodeDatabase.setChildNodes(createNodeDatabaseWithUpdates().getStoredChildNodes());
      saveCount++;
    } finally {
      reset();
    }
  }

  public void updateEntityOnTable(final TableViewDto tableView, EntityUpdateDto entityUpdate) {

    final Consumer<IMutableNode<?>> updateAction = d -> DATABASE_UPDATER.updateEntityOnTable(d, tableView,
      entityUpdate);

    addUpdateAction(updateAction);
  }

  private void addUpdateAction(final Consumer<IMutableNode<?>> updateAction) {
    updaterCollector.addUpdater(updateAction);
  }

  private IMutableNode<?> createNodeDatabaseWithUpdates() {

    final var newNodeDatabase = MutableNode.fromNode(nodeDatabase);

    updaterCollector.updateObjectAndClear(newNodeDatabase);

    return newNodeDatabase;
  }
}
