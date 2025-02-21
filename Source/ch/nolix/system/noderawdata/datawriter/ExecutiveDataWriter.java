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
import ch.nolix.systemapi.rawdataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class ExecutiveDataWriter {

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
    d -> DataWriterActionProvider.deleteEntriesFromMultiReference(d, tableView, entityId, multiReferenceColumnInfo);

    updaterCollector.addUpdater(updateAction);
  }

  public void deleteEntriesFromMultiValue(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiValueColumnInfo) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.deleteEntriesFromMultiValue(d, tableView, entityId, multiValueColumnInfo);

    updaterCollector.addUpdater(updateAction);
  }

  public void deleteEntryFromMultiReference(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiReferenceColumnInfo,
    final String referencedEntityId) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DataWriterActionProvider.deleteEntryFromMultiReference(
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
    d -> DataWriterActionProvider.deleteEntryFromMultiValue(d, tableView, entityId, multiValueColumnInfo, entry);

    updaterCollector.addUpdater(updateAction);
  }

  public void deleteEntityFromTable(final String tableName, final EntityDeletionDto entity) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.deleteEntityFromTable(d, tableName, entity);

    updaterCollector.addUpdater(updateAction);
  }

  public void deleteMultiBackReferenceEntry(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiBackReferenceColumnInfo,
    final String backReferencedEntityId) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DataWriterActionProvider.deleteMultiBackReferenceEntry(
      d,
      tableView,
      entityId,
      multiBackReferenceColumnInfo,
      backReferencedEntityId);

    updaterCollector.addUpdater(updateAction);
  }

  public void expectSchemaTimestamp(ITime schemaTimestamp) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.expectGivenSchemaTimestamp(d, schemaTimestamp);

    updaterCollector.addUpdater(updateAction);
  }

  public void expectTableContainsEntity(final String tableName, final String entityId) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.expectTableContainsEntity(d, tableName, entityId);

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
    DataWriterActionProvider.insertEntryIntoMultiBackReference(
      d,
      tableView,
      entityId,
      multiBackReferenceColumnInfo,
      backReferencedEntityId);

    updaterCollector.addUpdater(updateAction);
  }

  public void insertEntryIntoMultiReference(
    final MultiReferenceEntryDto multiReferenceEntry,
    final int multiReferenceColumnOneBasedOrdinalIndex) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DataWriterActionProvider.insertEntryIntoMultiReference(
      d,
      multiReferenceEntry,
      multiReferenceColumnOneBasedOrdinalIndex);

    updaterCollector.addUpdater(updateAction);
  }

  public void insertEntryIntoMultiValue(
    final TableSchemaViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiValueColumnInfo,
    final String entry) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.insertEntryIntoMultiValue(d, tableView, entityId, multiValueColumnInfo, entry);

    updaterCollector.addUpdater(updateAction);
  }

  public void insertEntityIntoTable(final TableSchemaViewDto tableView, final EntityCreationDto newEntity) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.insertEntityIntoTable(d, tableView, newEntity);

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
    d -> DataWriterActionProvider.updateEntityOnTable(d, tableView, entityUpdate);

    updaterCollector.addUpdater(updateAction);
  }

  private IMutableNode<?> createNodeDatabaseWithUpdates() {

    final var nodeDatabaseWithUpdates = MutableNode.fromNode(nodeDatabase);

    updaterCollector.updateObjectAndClear(nodeDatabaseWithUpdates);

    return nodeDatabaseWithUpdates;
  }
}
