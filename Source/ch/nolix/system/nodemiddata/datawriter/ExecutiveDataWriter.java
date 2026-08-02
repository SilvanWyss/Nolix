/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.datawriter;

import java.util.function.Consumer;

import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.programcontrol.updater.Updater;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.programcontrol.updater.IUpdater;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 */
public final class ExecutiveDataWriter {
  private final IMutableNode<?> nodeDatabase;

  private final IUpdater<IMutableNode<?>> updater = new Updater<>();

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

    updater.addUpdate(updateAction);
  }

  public void clearMultiValue(
    final String tableName,
    final String entityId,
    final int multiValueColumnOneBasedOrdinalIndex) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.clearMultiValue(d, tableName, entityId, multiValueColumnOneBasedOrdinalIndex);

    updater.addUpdate(updateAction);
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

    updater.addUpdate(updateAction);
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

    updater.addUpdate(updateAction);
  }

  public void deleteEntity(
    final String tableName,
    final String entityId,
    final String entitySaveStamp) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.deleteEntity(d, tableName, entityId, entitySaveStamp);

    updater.addUpdate(updateAction);
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

    updater.addUpdate(updateAction);
  }

  public void expectSchemaTimestamp(ITime schemaTimestamp) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.expectSchemaTimestamp(d, schemaTimestamp);

    updater.addUpdate(updateAction);
  }

  public void expectTableContainsEntity(final String tableName, final String entityId) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.expectTableContainsEntity(d, tableName, entityId);

    updater.addUpdate(updateAction);
  }

  public int getSaveCount() {
    return saveCount;
  }

  public boolean hasUpdates() {
    return updater.containsAny();
  }

  public void insertEntity(
    final String tableName,
    final String entityId,
    final INode<?> entityIndexNode,
    final INode<?> entityNode) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.insertEntity(d, tableName, entityId, entityIndexNode, entityNode);

    updater.addUpdate(updateAction);
  }

  public void insertMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final int multiBackReferenceColumnOneBasedOrdinalIndex,
    final INode<?> multiBackReferenceEntryNode) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DataWriterActionProvider.insertMultiBackReferenceEntry(
      d,
      tableName,
      entityId,
      multiBackReferenceColumnOneBasedOrdinalIndex,
      multiBackReferenceEntryNode);

    updater.addUpdate(updateAction);
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

    updater.addUpdate(updateAction);
  }

  public void insertMultiValueEntry(
    final String tableName,
    final String entityId,
    final int multiValueColumnOneBasedOrdinalIndex,
    final String value) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DataWriterActionProvider.insertMultiValueEntry(d, tableName, entityId, multiValueColumnOneBasedOrdinalIndex, value);

    updater.addUpdate(updateAction);
  }

  public void reset() {
    updater.clear();
  }

  public void saveChangesAndReset() {
    try {
      final var updatedNodeDatabase = MutableNode.fromNode(nodeDatabase);

      updater.updateObjectAndClear(updatedNodeDatabase);

      nodeDatabase.resetFromNode(updatedNodeDatabase);

      saveCount++;
    } finally {
      reset();
    }
  }

  public void updateEntity(final EntityUpdateDto entityUpdate, final TableInfoDto tableView) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.updateEntity(d, entityUpdate, tableView);

    updater.addUpdate(updateAction);
  }
}
