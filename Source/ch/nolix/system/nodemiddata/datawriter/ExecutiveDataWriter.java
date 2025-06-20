package ch.nolix.system.nodemiddata.datawriter;

import java.util.function.Consumer;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.process.UpdaterCollector;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programcontrolapi.processapi.IUpdaterCollector;
import ch.nolix.systemapi.middataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class ExecutiveDataWriter {

  private final IMutableNode<?> nodeDatabase;

  private final IUpdaterCollector<IMutableNode<?>> updaterCollector = new UpdaterCollector<>();

  private int saveCount;

  private ExecutiveDataWriter(final IMutableNode<?> nodeDatabase) {

    Validator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    this.nodeDatabase = nodeDatabase;
  }

  public static ExecutiveDataWriter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new ExecutiveDataWriter(nodeDatabase);
  }

  public void clearMultiReference(
    final String tableName,
    final String entityId,
    final int multiReferencedColumnOneBasedOrdinalIndex) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DataWriterActionProvider.clearMultiReference(d, tableName, entityId, multiReferencedColumnOneBasedOrdinalIndex);

    updaterCollector.addUpdater(updateAction);
  }

  public void clearMultiValue(
    final String tableName,
    final String entityId,
    final int multiValueColumnOneBasedOrdinalIndex) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.clearMultiValue(d, tableName, entityId, multiValueColumnOneBasedOrdinalIndex);

    updaterCollector.addUpdater(updateAction);
  }

  public void deleteMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final int multiReferencedColumnOneBasedOrdinalIndex,
    final String referencedEntityId) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DataWriterActionProvider.deleteMultiReferenceEntry(
      d,
      tableName,
      entityId,
      multiReferencedColumnOneBasedOrdinalIndex,
      referencedEntityId);

    updaterCollector.addUpdater(updateAction);
  }

  public void deleteMultiValueEntry(
    final String tableName,
    final String entityId,
    final int multiValueColumnOneBasedOrdinalIndex,
    final String entry) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DataWriterActionProvider.deleteMultiValueEntry(
      d,
      tableName,
      entityId,
      multiValueColumnOneBasedOrdinalIndex,
      entry);

    updaterCollector.addUpdater(updateAction);
  }

  public void deleteEntity(
    final String tableName,
    final String entityId,
    final String entitySaveStamp) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.deleteEntity(d, tableName, entityId, entitySaveStamp);

    updaterCollector.addUpdater(updateAction);
  }

  public void deleteMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final int multiBackReferenceColumnOneBasedOrdinalIndex,
    final String backReferencedEntityId) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DataWriterActionProvider.deleteMultiBackReferenceEntry(
      d,
      tableName,
      entityId,
      multiBackReferenceColumnOneBasedOrdinalIndex,
      backReferencedEntityId);

    updaterCollector.addUpdater(updateAction);
  }

  public void expectSchemaTimestamp(ITime schemaTimestamp) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.expectSchemaTimestamp(d, schemaTimestamp);

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

  public void insertEntity(
    final String tableName,
    final String entityId,
    final INode<?> entityIndexNode,
    final INode<?> entityNode) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.insertEntity(d, tableName, entityId, entityIndexNode, entityNode);

    updaterCollector.addUpdater(updateAction);
  }

  public void insertMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final int multiBackReferenceColumnOneBasedOrdinalIndex,
    final String backReferencedEntityId,
    final String backReferencedEntityTableId) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DataWriterActionProvider.insertMultiBackReferenceEntry(
      d,
      tableName,
      entityId,
      multiBackReferenceColumnOneBasedOrdinalIndex,
      backReferencedEntityId,
      backReferencedEntityTableId);

    updaterCollector.addUpdater(updateAction);
  }

  public void insertMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final int multiReferenceColumnOneBasedOrdinalIndex,
    final INode<?> multiReferenceEntryNode) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DataWriterActionProvider.insertMultiReferenceEntry(
      d,
      tableName,
      entityId,
      multiReferenceColumnOneBasedOrdinalIndex,
      multiReferenceEntryNode);

    updaterCollector.addUpdater(updateAction);
  }

  public void insertMultiValueEntry(
    final String tableName,
    final String entityId,
    final int multiValueColumnOneBasedOrdinalIndex,
    final String value) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DataWriterActionProvider.insertMultiValueEntry(d, tableName, entityId, multiValueColumnOneBasedOrdinalIndex, value);

    updaterCollector.addUpdater(updateAction);
  }

  public void reset() {
    updaterCollector.clear();
  }

  public void saveChangesAndReset() {
    try {

      final var updatedNodeDatabase = MutableNode.fromNode(nodeDatabase);

      updaterCollector.updateObjectAndClear(updatedNodeDatabase);

      nodeDatabase.resetFromNode(updatedNodeDatabase);

      saveCount++;
    } finally {
      reset();
    }
  }

  public void updateEntity(final EntityUpdateDto entityUpdate, final TableViewDto tableView) {

    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.updateEntity(d, entityUpdate, tableView);

    updaterCollector.addUpdater(updateAction);
  }
}
