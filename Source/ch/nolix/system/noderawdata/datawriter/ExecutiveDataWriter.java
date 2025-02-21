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
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class ExecutiveDataWriter {

  private static final DataWriterActionProvider DATA_WRITER_ACTION_PROVIDER = new DataWriterActionProvider();

  private final IMutableNode<?> nodeDatabase;

  private final IUpdaterCollector<IMutableNode<?>> updateCollector = new UpdaterCollector<>();

  private int saveCount;

  public ExecutiveDataWriter(final IMutableNode<?> nodeDatabase) {

    GlobalValidator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    this.nodeDatabase = nodeDatabase;
  }

  public void deleteEntriesFromMultiReference(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiReferenceColumnInfo) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.deleteEntriesFromMultiReference(d, tableView, entityId, multiReferenceColumnInfo);

    updateCollector.addUpdater(updateAction);
  }

  public void deleteEntriesFromMultiValue(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiValueColumnInfo) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.deleteEntriesFromMultiValue(d, tableView, entityId, multiValueColumnInfo);

    updateCollector.addUpdater(updateAction);
  }

  public void deleteEntryFromMultiReference(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiReferenceColumnInfo,
    final String referencedEntityId) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DATA_WRITER_ACTION_PROVIDER.deleteEntryFromMultiReference(
      d,
      tableView,
      entityId,
      multiReferenceColumnInfo,
      referencedEntityId);

    updateCollector.addUpdater(updateAction);
  }

  public void deleteEntryFromMultiValue(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiValueColumnInfo,
    final String entry) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.deleteEntryFromMultiValue(d, tableView, entityId, multiValueColumnInfo, entry);

    updateCollector.addUpdater(updateAction);
  }

  public void deleteEntityFromTable(final String tableName, final EntityDeletionDto entity) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.deleteEntityFromTable(d, tableName, entity);

    updateCollector.addUpdater(updateAction);
  }

  public void deleteMultiBackReferenceEntry(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiBackReferenceColumnInfo,
    final String backReferencedEntityId) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DATA_WRITER_ACTION_PROVIDER.deleteMultiBackReferenceEntry(
      d,
      tableView,
      entityId,
      multiBackReferenceColumnInfo,
      backReferencedEntityId);

    updateCollector.addUpdater(updateAction);
  }

  public void expectSchemaTimestamp(ITime schemaTimestamp) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.expectGivenSchemaTimestamp(d, schemaTimestamp);

    updateCollector.addUpdater(updateAction);
  }

  public void expectTableContainsEntity(final String tableName, final String entityId) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.expectTableContainsEntity(d, tableName, entityId);

    updateCollector.addUpdater(updateAction);
  }

  public int getSaveCount() {
    return saveCount;
  }

  public boolean hasUpdates() {
    return updateCollector.containsAny();
  }

  public void insertEntryIntoMultiBackReference(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiBackReferenceColumnInfo,
    final String backReferencedEntityId) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DATA_WRITER_ACTION_PROVIDER.insertEntryIntoMultiBackReference(
      d,
      tableView,
      entityId,
      multiBackReferenceColumnInfo,
      backReferencedEntityId);

    updateCollector.addUpdater(updateAction);
  }

  public void insertEntryIntoMultiReference(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiReferenceColumnInfo,
    final String referencedEntityId) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DATA_WRITER_ACTION_PROVIDER.insertEntryIntoMultiReference(
      d,
      tableView,
      entityId,
      multiReferenceColumnInfo,
      referencedEntityId);

    updateCollector.addUpdater(updateAction);
  }

  public void insertEntryIntoMultiValue(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiValueColumnInfo,
    final String entry) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.insertEntryIntoMultiValue(d, tableView, entityId, multiValueColumnInfo, entry);

    updateCollector.addUpdater(updateAction);
  }

  public void insertEntityIntoTable(final TableSchemaViewDto tableView, final EntityCreationDto newEntity) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.insertEntityIntoTable(d, tableView, newEntity);

    updateCollector.addUpdater(updateAction);
  }

  public void reset() {
    updateCollector.clear();
  }

  public void saveChangesAndReset() {
    try {

      nodeDatabase.resetFromNode(createNodeDatabaseWithUpdates());

      saveCount++;
    } finally {
      reset();
    }
  }

  public void updateEntityOnTable(final TableSchemaViewDto tableView, EntityUpdateDto entityUpdate) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.updateEntityOnTable(d, tableView, entityUpdate);

    updateCollector.addUpdater(updateAction);
  }

  private IMutableNode<?> createNodeDatabaseWithUpdates() {

    final var newNodeDatabase = MutableNode.fromNode(nodeDatabase);

    updateCollector.updateObjectAndClear(newNodeDatabase);

    return newNodeDatabase;
  }
}
