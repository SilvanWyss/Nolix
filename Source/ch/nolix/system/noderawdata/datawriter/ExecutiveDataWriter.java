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

  private final IUpdaterCollector<IMutableNode<?>> updaterCollector = new UpdaterCollector<>();

  private int saveCount;

  private ExecutiveDataWriter(final IMutableNode<?> nodeDatabase) {

    GlobalValidator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    this.nodeDatabase = nodeDatabase;
  }

  public static ExecutiveDataWriter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new ExecutiveDataWriter(nodeDatabase);
  }

  public void deleteEntriesFromMultiReference(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiReferenceColumnInfo) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.deleteEntriesFromMultiReference(d, tableView, entityId, multiReferenceColumnInfo);

    updaterCollector.addUpdater(updateAction);
  }

  public void deleteEntriesFromMultiValue(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiValueColumnInfo) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.deleteEntriesFromMultiValue(d, tableView, entityId, multiValueColumnInfo);

    updaterCollector.addUpdater(updateAction);
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

    updaterCollector.addUpdater(updateAction);
  }

  public void deleteEntryFromMultiValue(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiValueColumnInfo,
    final String entry) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.deleteEntryFromMultiValue(d, tableView, entityId, multiValueColumnInfo, entry);

    updaterCollector.addUpdater(updateAction);
  }

  public void deleteEntityFromTable(final String tableName, final EntityDeletionDto entity) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.deleteEntityFromTable(d, tableName, entity);

    updaterCollector.addUpdater(updateAction);
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

    updaterCollector.addUpdater(updateAction);
  }

  public void expectSchemaTimestamp(ITime schemaTimestamp) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.expectGivenSchemaTimestamp(d, schemaTimestamp);

    updaterCollector.addUpdater(updateAction);
  }

  public void expectTableContainsEntity(final String tableName, final String entityId) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.expectTableContainsEntity(d, tableName, entityId);

    updaterCollector.addUpdater(updateAction);
  }

  public int getSaveCount() {
    return saveCount;
  }

  public boolean hasUpdates() {
    return updaterCollector.containsAny();
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

    updaterCollector.addUpdater(updateAction);
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

    updaterCollector.addUpdater(updateAction);
  }

  public void insertEntryIntoMultiValue(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiValueColumnInfo,
    final String entry) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.insertEntryIntoMultiValue(d, tableView, entityId, multiValueColumnInfo, entry);

    updaterCollector.addUpdater(updateAction);
  }

  public void insertEntityIntoTable(final TableSchemaViewDto tableView, final EntityCreationDto newEntity) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DATA_WRITER_ACTION_PROVIDER.insertEntityIntoTable(d, tableView, newEntity);

    updaterCollector.addUpdater(updateAction);
  }

  public void reset() {
    updaterCollector.clear();
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

    updaterCollector.addUpdater(updateAction);
  }

  private IMutableNode<?> createNodeDatabaseWithUpdates() {

    final var nodeDatabaseWithUpdates = MutableNode.fromNode(nodeDatabase);

    updaterCollector.updateObjectAndClear(nodeDatabaseWithUpdates);

    return nodeDatabaseWithUpdates;
  }
}
