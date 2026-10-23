/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.datawriter;

import java.util.function.Consumer;

import ch.nolix.base.document.node.MutableNode;
import ch.nolix.base.programcontrol.updater.Updater;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.programcontrol.updater.IUpdater;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.time.main.ITime;

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

  /**
   * @param nodeDatabase
   * @return a new {@link ExecutiveDataWriter} for the given nodeDatabase
   */
  public static ExecutiveDataWriter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new ExecutiveDataWriter(nodeDatabase);
  }

  /**
   * Adds a change to clear the multi reference at the given
   * multiReferencedColumnOneBasedOrdinalIndex of the entity with the given
   * entityId of the table with the given tableName.
   * 
   * @param tableName
   * @param entityId
   * @param multiReferencedColumnOneBasedOrdinalIndex
   */
  public void clearMultiReference(
    final String tableName,
    final String entityId,
    final int multiReferencedColumnOneBasedOrdinalIndex) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> //
    DataWriterActionProvider.clearMultiReference(d, tableName, entityId, multiReferencedColumnOneBasedOrdinalIndex);

    updater.addUpdate(updateAction);
  }

  /**
   * Adds a change to clear the multi value at the given
   * multiValueColumnOneBasedOrdinalIndex of the entity with the given entityId of
   * the table with the given tableName.
   * 
   * @param tableName
   * @param entityId
   * @param multiValueColumnOneBasedOrdinalIndex
   */
  public void clearMultiValue(
    final String tableName,
    final String entityId,
    final int multiValueColumnOneBasedOrdinalIndex) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.clearMultiValue(d, tableName, entityId, multiValueColumnOneBasedOrdinalIndex);

    updater.addUpdate(updateAction);
  }

  /**
   * Adds a change to delete the entity with the given entityId and
   * entitySaveStamp of the table with the given tableName.
   * 
   * @param tableName
   * @param entityId
   * @param entitySaveStamp
   */
  public void deleteEntity(
    final String tableName,
    final String entityId,
    final String entitySaveStamp) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.deleteEntity(d, tableName, entityId, entitySaveStamp);

    updater.addUpdate(updateAction);
  }

  /**
   * Adds a change to delete the entry with the given backReferencedEntityId from
   * the multi back reference at the given
   * multiBackReferenceColumnOneBasedOrdinalIndex of the entity with the given
   * entityId of the table with the given tableName.
   * 
   * @param tableName
   * @param entityId
   * @param multiBackReferenceColumnOneBasedOrdinalIndex
   * @param backReferencedEntityId
   */
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

  /**
   * Adds a change to delete the entry with the given referencedEntityId from the
   * multi reference at the given multiReferencedColumnOneBasedOrdinalIndex of the
   * entity with the given entityId of the table with the given tableName.
   * 
   * @param tableName
   * @param entityId
   * @param multiReferencedColumnOneBasedOrdinalIndex
   * @param referencedEntityId
   */
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

  /**
   * Adds a change to delete the given entry from the multi value at the given
   * multiValueColumnOneBasedOrdinalIndex of the entity with the given entityId of
   * the table with the given tableName.
   * 
   * @param tableName
   * @param entityId
   * @param multiValueColumnOneBasedOrdinalIndex
   * @param entry
   */
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

  /**
   * Adds an expectation that the database has the given schemaTimestamp.
   * 
   * @param schemaTimestamp
   */
  public void expectSchemaTimestamp(ITime schemaTimestamp) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.expectSchemaTimestamp(d, schemaTimestamp);

    updater.addUpdate(updateAction);
  }

  /**
   * Adds an expectation that the table with the given tableName contains the
   * entity with the given entityId.
   * 
   * @param tableName
   * @param entityId
   */
  public void expectTableContainsEntity(final String tableName, final String entityId) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.expectTableContainsEntity(d, tableName, entityId);

    updater.addUpdate(updateAction);
  }

  /**
   * @return the number of saves of the current {@link ExecutiveDataWriter}
   */
  public int getSaveCount() {
    return saveCount;
  }

  /**
   * @return true if the current {@link ExecutiveDataWriter} has updates, false
   *         otherwise
   */
  public boolean hasUpdates() {
    return updater.containsAny();
  }

  /**
   * Adds a change to insert the given entityNode into the table with the given
   * tableName and the given entityIndexNode for the entity with the given
   * entityId.
   * 
   * @param tableName
   * @param entityId
   * @param entityIndexNode
   * @param entityNode
   */
  public void insertEntity(
    final String tableName,
    final String entityId,
    final Node<?> entityIndexNode,
    final Node<?> entityNode) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.insertEntity(d, tableName, entityId, entityIndexNode, entityNode);

    updater.addUpdate(updateAction);
  }

  /**
   * Adds a change to insert the given multiBackReferenceEntryNode into the multi
   * back reference at the given multiBackReferenceColumnOneBasedOrdinalIndex of
   * the entity with the given entityId of the table with the given tableName.
   * 
   * @param tableName
   * @param entityId
   * @param multiBackReferenceColumnOneBasedOrdinalIndex
   * @param multiBackReferenceEntryNode
   */
  public void insertMultiBackReferenceEntry(
    final String tableName,
    final String entityId,
    final int multiBackReferenceColumnOneBasedOrdinalIndex,
    final Node<?> multiBackReferenceEntryNode) {
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

  /**
   * Adds a change to insert the given multiReferenceEntryNode into the multi
   * reference at the given multiReferenceColumnOneBasedOrdinalIndex of the entity
   * with the given entityId of the table with the given tableName.
   * 
   * @param tableName
   * @param entityId
   * @param multiReferenceColumnOneBasedOrdinalIndex
   * @param multiReferenceEntryNode
   */
  public void insertMultiReferenceEntry(
    final String tableName,
    final String entityId,
    final int multiReferenceColumnOneBasedOrdinalIndex,
    final Node<?> multiReferenceEntryNode) {
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

  /**
   * Adds a change to insert the given value into the multi value at the given
   * multiValueColumnOneBasedOrdinalIndex of the entity with the given entityId of
   * the table with the given tableName.
   * 
   * @param tableName
   * @param entityId
   * @param multiValueColumnOneBasedOrdinalIndex
   * @param value
   */
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

  /**
   * Removes all changes and expectations of the current
   * {@link ExecutiveDataWriter}.
   */
  public void reset() {
    updater.clear();
  }

  /**
   * Saves the changes of the current {@link ExecutiveDataWriter} and resets the
   * current {@link ExecutiveDataWriter}.
   */
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

  /**
   * Adds a change to apply the given entityUpdate using the given tableView.
   * 
   * @param entityUpdate
   * @param tableView
   */
  public void updateEntity(final EntityUpdateDto entityUpdate, final TableInfoDto tableView) {
    final Consumer<IMutableNode<?>> updateAction = //
    d -> DataWriterActionProvider.updateEntity(d, entityUpdate, tableView);

    updater.addUpdate(updateAction);
  }
}
