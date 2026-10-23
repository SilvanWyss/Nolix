/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.datawriter;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.errorcontrol.generalexception.ChangedResourceException;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PluralLowerCaseVariableNameCatalog;
import ch.nolix.system.midschemainfo.modelsearcher.TableInfoSearcher;
import ch.nolix.system.nodemiddata.nodeeditor.TableNodeEditor;
import ch.nolix.system.nodemiddata.nodeexaminer.TableNodeExaminer;
import ch.nolix.system.nodemiddata.nodemapper.ContentFieldNodeMapper;
import ch.nolix.system.nodemiddata.nodesearcher.EntityNodeSearcher;
import ch.nolix.system.nodemiddata.nodesearcher.TableNodeSearcher;
import ch.nolix.system.nodemiddata.nodevalidator.TableNodeValidator;
import ch.nolix.system.nodemidschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.system.nodemidschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.database.databaseproperty.BaseCardinality;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public final class DataWriterActionProvider {
  private static final DatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final DatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final TableInfoSearcher TABLE_VIEW_SEARCHER = new TableInfoSearcher();

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final TableNodeExaminer TABLE_NODE_EXAMINER = new TableNodeExaminer();

  private static final TableNodeValidator TABLE_NODE_VALIDATOR = new TableNodeValidator();

  private static final TableNodeEditor TABLE_NODE_EDITOR = new TableNodeEditor();

  private static final EntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  private static final ContentFieldNodeMapper CONTENT_FIELD_NODE_MAPPER = new ContentFieldNodeMapper();

  private DataWriterActionProvider() {
  }

  /**
   * Clears the multi reference at the given
   * multiReferencedColumnOneBasedOrdinalIndex of the entity with the given
   * entityId of the table with the given tableName in the given nodeDatabase.
   * 
   * @param nodeDatabase
   * @param tableName
   * @param entityId
   * @param multiReferencedColumnOneBasedOrdinalIndex
   */
  public static void clearMultiReference(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiReferencedColumnOneBasedOrdinalIndex) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNode(tableNode, entityId);

    final var multiReferenceNode = //
    entityNode.getStoredChildNodeAtOneBasedIndex(multiReferencedColumnOneBasedOrdinalIndex);

    multiReferenceNode.reset();
  }

  /**
   * Clears the multi value at the given multiValueColumnOneBasedOrdinalIndex of
   * the entity with the given entityId of the table with the given tableName in
   * the given nodeDatabase.
   * 
   * @param nodeDatabase
   * @param tableName
   * @param entityId
   * @param multiValueColumnOneBasedOrdinalIndex
   */
  public static void clearMultiValue(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiValueColumnOneBasedOrdinalIndex) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNode(tableNode, entityId);
    final var multiValueNode = entityNode.getStoredChildNodeAtOneBasedIndex(multiValueColumnOneBasedOrdinalIndex);

    multiValueNode.reset();
  }

  /**
   * Deletes the entity with the given entityId of the table with the given
   * tableName from the given database.
   * 
   * @param database
   * @param tableName
   * @param entityId
   * @param entitySaveStamp
   * @throws RuntimeException if the entity with the given entityId does not have
   *                          the given entitySaveStamp
   */
  public static void deleteEntity(
    final IMutableNode<?> database,
    final String tableName,
    final String entityId,
    final String entitySaveStamp) {
    deleteEntityIndex(database, entityId);

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(database, tableName);
    final var entityNode = TABLE_NODE_EDITOR.removeAndGetStoredEntityNodeById(tableNode, entityId);
    final var saveStampNode = ENTITY_NODE_SEARCHER.getStoredSaveStampNodeFromEntityNode(entityNode);

    if (!saveStampNode.hasHeader(entitySaveStamp)) {
      throw ChangedResourceException.forResource("data");
    }
  }

  /**
   * Deletes the entry with the given backReferencedEntityId from the multi back
   * reference at the given multiBackReferenceColumnOneBasedOrdinalIndex of the
   * entity with the given entityId of the table with the given tableName in the
   * given nodeDatabase.
   * 
   * @param nodeDatabase
   * @param tableName
   * @param entityId
   * @param multiBackReferenceColumnOneBasedOrdinalIndex
   * @param backReferencedEntityId
   */
  public static void deleteMultiBackReferenceEntry(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiBackReferenceColumnOneBasedOrdinalIndex,
    final String backReferencedEntityId) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNode(tableNode, entityId);

    final var multiBackReferenceColumnNode = //
    entityNode.getStoredChildNodeAtOneBasedIndex(multiBackReferenceColumnOneBasedOrdinalIndex);

    multiBackReferenceColumnNode.removeFirstChildNodeWithHeader(backReferencedEntityId);
  }

  /**
   * Deletes the entry with the given referencedEntityId from the multi reference
   * at the given multiReferencedColumnOneBasedOrdinalIndex of the entity with the
   * given entityId of the table with the given tableName in the given
   * nodeDatabase.
   * 
   * @param nodeDatabase
   * @param tableName
   * @param entityId
   * @param multiReferencedColumnOneBasedOrdinalIndex
   * @param referencedEntityId
   */
  public static void deleteMultiReferenceEntry(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiReferencedColumnOneBasedOrdinalIndex,
    final String referencedEntityId) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNode(tableNode, entityId);

    final var multiReferenceNode = //
    entityNode.getStoredChildNodeAtOneBasedIndex(multiReferencedColumnOneBasedOrdinalIndex);

    multiReferenceNode.removeFirstChildNodeThat(
      n -> n.getStoredChildNodeAtOneBasedIndex(1).hasHeader(referencedEntityId));
  }

  /**
   * Deletes the given entry from the multi value at the given
   * multiValueColumnOneBasedOrdinalIndex of the entity with the given entityId of
   * the table with the given tableName in the given nodeDatabase.
   * 
   * @param nodeDatabase
   * @param tableName
   * @param entityId
   * @param multiValueColumnOneBasedOrdinalIndex
   * @param entry
   */
  public static void deleteMultiValueEntry(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiValueColumnOneBasedOrdinalIndex,
    final String entry) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNode(tableNode, entityId);
    final var multiValueNode = entityNode.getStoredChildNodeAtOneBasedIndex(multiValueColumnOneBasedOrdinalIndex);

    multiValueNode.removeFirstChildNodeWithHeader(entry);
  }

  /**
   * @param nodeDatabase
   * @param schemaTimestamp
   * @throws RuntimeException if the given nodeDatabase does not have the given
   *                          schemaTimestamp
   */
  public static void expectSchemaTimestamp(final IMutableNode<?> nodeDatabase, final ITime schemaTimestamp) {
    final var databasePropertiesNode = //
    DATABASE_NODE_SEARCHER.getStoredDatabasePropertiesNodeFromNodeDatabase(nodeDatabase);

    final var actualSchemaTimestamp = //
    DATABASE_PROPERTIES_NODE_SEARCHER.getSchemaTimestampFromDatabasePropertiesNode(databasePropertiesNode);

    if (!actualSchemaTimestamp.equals(schemaTimestamp)) {
      throw ChangedResourceException.forResource(LowerCaseVariableNameCatalog.SCHEMA);
    }
  }

  /**
   * @param nodeDatabase
   * @param tableName
   * @param entityId
   * @throws RuntimeException if the table with the given tableName in the given
   *                          nodeDatabase does not contain an entity with the
   *                          given entityId
   */
  public static void expectTableContainsEntity(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    TABLE_NODE_VALIDATOR.assertTableNodeContainsEntityWithId(tableNode, entityId);
  }

  /**
   * Inserts the given entityNode into the table with the given tableName in the
   * given nodeDatabase and the given entityIndexNode for the entity with the
   * given entityId.
   * 
   * @param nodeDatabase
   * @param tableName
   * @param entityId
   * @param entityIndexNode
   * @param entityNode
   */
  public static void insertEntity(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final Node<?> entityIndexNode,
    final Node<?> entityNode) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    if (TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWithGivenId(tableNode, entityId)) {
      throw //
      ArgumentHasAttributeException.forArgumentAndAttributeName(tableNode, "entity with the id '" + entityId + "'");
    }

    final var entityIndexesNode = DATABASE_NODE_SEARCHER.getStoredEntityIndexesNodeFromNodeDatabase(nodeDatabase);

    entityIndexesNode.addChildNode(entityIndexNode);
    tableNode.addChildNode(entityNode);
  }

  /**
   * Inserts the given multiBackReferenceEntryNode into the multi back reference
   * at the given multiBackReferenceColumnOneBasedOrdinalIndex of the entity with
   * the given entityId of the table with the given tableName in the given
   * nodeDatabase.
   * 
   * @param nodeDatabase
   * @param tableName
   * @param entityId
   * @param multiBackReferenceColumnOneBasedOrdinalIndex
   * @param multiBackReferenceEntryNode
   */
  public static void insertMultiBackReferenceEntry(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiBackReferenceColumnOneBasedOrdinalIndex,
    final Node<?> multiBackReferenceEntryNode) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNode(tableNode, entityId);

    final var multiBackReferenceNode = //
    entityNode.getStoredChildNodeAtOneBasedIndex(multiBackReferenceColumnOneBasedOrdinalIndex);

    multiBackReferenceNode.addChildNode(multiBackReferenceEntryNode);
  }

  /**
   * Inserts the given multiReferenceEntryNode into the multi reference at the
   * given multiReferenceColumnOneBasedOrdinalIndex of the entity with the given
   * entityId of the table with the given tableName in the given nodeDatabase.
   * 
   * @param nodeDatabase
   * @param tableName
   * @param entityId
   * @param multiReferenceColumnOneBasedOrdinalIndex
   * @param multiReferenceEntryNode
   */
  public static void insertMultiReferenceEntry(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiReferenceColumnOneBasedOrdinalIndex,
    final Node<?> multiReferenceEntryNode) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNode(tableNode, entityId);

    final var multiReferenceNode = //
    entityNode.getStoredChildNodeAtOneBasedIndex(multiReferenceColumnOneBasedOrdinalIndex);

    multiReferenceNode.addChildNode(multiReferenceEntryNode);
  }

  /**
   * Inserts the given value into the multi value at the given
   * multiValueColumnOneBasedOrdinalIndex of the entity with the given entityId of
   * the table with the given tableName in the given nodeDatabase.
   * 
   * @param nodeDatabase
   * @param tableName
   * @param entityId
   * @param multiValueColumnOneBasedOrdinalIndex
   * @param value
   */
  public static void insertMultiValueEntry(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiValueColumnOneBasedOrdinalIndex,
    final String value) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNode(tableNode, entityId);
    final var multiValueNode = entityNode.getStoredChildNodeAtOneBasedIndex(multiValueColumnOneBasedOrdinalIndex);
    final var multiValueValueNode = ImmutableNode.withHeader(value);

    multiValueNode.addChildNode(multiValueValueNode);
  }

  /**
   * Applies the given entityUpdate on the given database using the given
   * tableView.
   * 
   * @param database
   * @param entityUpdate
   * @param tableView
   */
  public static void updateEntity(
    final IMutableNode<?> database,
    final EntityUpdateDto entityUpdate,
    final TableInfoDto tableView) {
    final var tableName = tableView.name();
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(database, tableName);
    final var entityId = entityUpdate.id();
    final var entityNodeContainer = TABLE_NODE_SEARCHER.getOptionalStoredEntity(tableNode, entityId);

    if (entityNodeContainer.isEmpty()) {
      throw ChangedResourceException.forResource(PluralLowerCaseVariableNameCatalog.DATA);
    }

    final var saveStamp = entityUpdate.saveStamp();
    final var entityNode = entityNodeContainer.get();
    final var saveStampNode = ENTITY_NODE_SEARCHER.getStoredSaveStampNodeFromEntityNode(entityNode);
    final var actualSaveStamp = saveStampNode.getHeader();

    if (!saveStamp.equals(actualSaveStamp)) {
      throw ChangedResourceException.forResource(PluralLowerCaseVariableNameCatalog.DATA);
    }

    final var newSaveStamp = String.valueOf(Integer.valueOf(saveStamp) + 1);
    saveStampNode.setHeader(newSaveStamp);

    updateContentFieldsOfEntityNode(entityNode, entityUpdate, tableView);
  }

  private static void deleteEntityIndex(final IMutableNode<?> nodeDatabase, final String entityId) {
    final var entityIndexesNode = DATABASE_NODE_SEARCHER.getStoredEntityIndexesNodeFromNodeDatabase(nodeDatabase);

    entityIndexesNode.removeFirstChildNodeThat(ehn -> ehn.getStoredChildNodeAtOneBasedIndex(2).hasHeader(entityId));
  }

  private static void updateContentFieldsOfEntityNode(
    final IMutableNode<?> entityNode,
    final EntityUpdateDto entityUpdate,
    final TableInfoDto tableView) {
    for (final var f : entityUpdate.updatedContentFields()) {
      final var columnName = f.columnName();
      final var columnView = TABLE_VIEW_SEARCHER.getColumnViewByColumnName(tableView, columnName);

      if (columnView.fieldType().getCardinality().getBaseCardinality() == BaseCardinality.SINGLE) {
        final var oneBasedColumnIndex = columnView.oneBasedOrdinalIndex();
        final var fieldNode = entityNode.getStoredChildNodeAtOneBasedIndex(oneBasedColumnIndex);
        final var newFieldNode = CONTENT_FIELD_NODE_MAPPER.mapValueStringFieldDtoToContentFieldNode(f);

        fieldNode.resetFromNode(newFieldNode);
      }
    }
  }
}
