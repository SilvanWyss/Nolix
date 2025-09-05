package ch.nolix.system.nodemiddata.datawriter;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.generalexception.ChangedResourceException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.coreapi.datamodel.cardinality.BaseCardinality;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.misc.variable.PluralLowerCaseVariableCatalog;
import ch.nolix.system.midschemaview.modelsearcher.TableViewSearcher;
import ch.nolix.system.nodemiddata.nodeeditor.TableNodeEditor;
import ch.nolix.system.nodemiddata.nodeexaminer.TableNodeExaminer;
import ch.nolix.system.nodemiddata.nodemapper.ContentFieldNodeMapper;
import ch.nolix.system.nodemiddata.nodesearcher.EntityNodeSearcher;
import ch.nolix.system.nodemiddata.nodesearcher.TableNodeSearcher;
import ch.nolix.system.nodemiddata.nodevalidator.TableNodeValidator;
import ch.nolix.system.nodemidschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.system.nodemidschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;
import ch.nolix.systemapi.midschemaview.modelsearcher.ITableViewSearcher;
import ch.nolix.systemapi.nodemiddata.nodeeditor.ITableNodeEditor;
import ch.nolix.systemapi.nodemiddata.nodeexaminer.ITableNodeExaminer;
import ch.nolix.systemapi.nodemiddata.nodemapper.IContentFieldNodeMapper;
import ch.nolix.systemapi.nodemiddata.nodesearcher.IEntityNodeSearcher;
import ch.nolix.systemapi.nodemiddata.nodesearcher.ITableNodeSearcher;
import ch.nolix.systemapi.nodemiddata.nodevalidator.ITableNodeValidator;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IDatabaseNodeSearcher;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.time.moment.ITime;

public final class DataWriterActionProvider {
  private static final IDatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final IDatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final ITableViewSearcher TABLE_VIEW_SEARCHER = new TableViewSearcher();

  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final ITableNodeExaminer TABLE_NODE_EXAMINER = new TableNodeExaminer();

  private static final ITableNodeValidator TABLE_NODE_VALIDATOR = new TableNodeValidator();

  private static final ITableNodeEditor TABLE_NODE_EDITOR = new TableNodeEditor();

  private static final IEntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  private static final IContentFieldNodeMapper CONTENT_FIELD_NODE_MAPPER = new ContentFieldNodeMapper();

  private DataWriterActionProvider() {
  }

  public static void clearMultiReference(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiReferencedColumnOneBasedOrdinalIndex) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceNode = //
    entityNode.getStoredChildNodeAtOneBasedIndex(multiReferencedColumnOneBasedOrdinalIndex);

    multiReferenceNode.reset();
  }

  public static void clearMultiValue(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiValueColumnOneBasedOrdinalIndex) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);
    final var multiValueNode = entityNode.getStoredChildNodeAtOneBasedIndex(multiValueColumnOneBasedOrdinalIndex);

    multiValueNode.reset();
  }

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

  public static void deleteMultiBackReferenceEntry(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiBackReferenceColumnOneBasedOrdinalIndex,
    final String backReferencedEntityId) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiBackReferenceColumnNode = //
    entityNode.getStoredChildNodeAtOneBasedIndex(multiBackReferenceColumnOneBasedOrdinalIndex);

    multiBackReferenceColumnNode.removeFirstChildNodeWithHeader(backReferencedEntityId);
  }

  public static void deleteMultiReferenceEntry(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiReferencedColumnOneBasedOrdinalIndex,
    final String referencedEntityId) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceNode = //
    entityNode.getStoredChildNodeAtOneBasedIndex(multiReferencedColumnOneBasedOrdinalIndex);

    multiReferenceNode.removeFirstChildNodeThat(
      n -> n.getStoredChildNodeAtOneBasedIndex(1).hasHeader(referencedEntityId));
  }

  public static void deleteMultiValueEntry(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiValueColumnOneBasedOrdinalIndex,
    final String entry) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);
    final var multiValueNode = entityNode.getStoredChildNodeAtOneBasedIndex(multiValueColumnOneBasedOrdinalIndex);

    multiValueNode.removeFirstChildNodeWithHeader(entry);
  }

  public static void expectSchemaTimestamp(final IMutableNode<?> nodeDatabase, final ITime schemaTimestamp) {
    final var databasePropertiesNode = //
    DATABASE_NODE_SEARCHER.getStoredDatabasePropertiesNodeFromNodeDatabase(nodeDatabase);

    final var actualSchemaTimestamp = //
    DATABASE_PROPERTIES_NODE_SEARCHER.getSchemaTimestampFromDatabasePropertiesNode(databasePropertiesNode);

    if (!actualSchemaTimestamp.equals(schemaTimestamp)) {
      throw ChangedResourceException.forResource(LowerCaseVariableCatalog.SCHEMA);
    }
  }

  public static void expectTableContainsEntity(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    TABLE_NODE_VALIDATOR.assertTableNodeContainsEntityWithId(tableNode, entityId);
  }

  public static void insertEntity(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final INode<?> entityIndexNode,
    final INode<?> entityNode) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    if (TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWithGivenId(tableNode, entityId)) {
      throw //
      ArgumentHasAttributeException.forArgumentAndAttributeName(tableNode, "entity with the id '" + entityId + "'");
    }

    final var entityIndexesNode = DATABASE_NODE_SEARCHER.getStoredEntityIndexesNodeFromNodeDatabase(nodeDatabase);

    entityIndexesNode.addChildNode(entityIndexNode);
    tableNode.addChildNode(entityNode);
  }

  public static void insertMultiBackReferenceEntry(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiBackReferenceColumnOneBasedOrdinalIndex,
    final INode<?> multiBackReferenceEntryNode) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiBackReferenceNode = //
    entityNode.getStoredChildNodeAtOneBasedIndex(multiBackReferenceColumnOneBasedOrdinalIndex);

    multiBackReferenceNode.addChildNode(multiBackReferenceEntryNode);
  }

  public static void insertMultiReferenceEntry(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiReferenceColumnOneBasedOrdinalIndex,
    final INode<?> multiReferenceEntryNode) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceNode = //
    entityNode.getStoredChildNodeAtOneBasedIndex(multiReferenceColumnOneBasedOrdinalIndex);

    multiReferenceNode.addChildNode(multiReferenceEntryNode);
  }

  public static void insertMultiValueEntry(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiValueColumnOneBasedOrdinalIndex,
    final String value) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);
    final var multiValueNode = entityNode.getStoredChildNodeAtOneBasedIndex(multiValueColumnOneBasedOrdinalIndex);
    final var multiValueValueNode = Node.withHeader(value);

    multiValueNode.addChildNode(multiValueValueNode);
  }

  public static void updateEntity(
    final IMutableNode<?> database,
    final EntityUpdateDto entityUpdate,
    final TableViewDto tableView) {
    final var tableName = tableView.name();
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(database, tableName);
    final var entityId = entityUpdate.id();
    final var entityNodeContainer = TABLE_NODE_SEARCHER.getOptionalStoredEntityNodeFromTableNode(tableNode, entityId);

    if (entityNodeContainer.isEmpty()) {
      throw ChangedResourceException.forResource(PluralLowerCaseVariableCatalog.DATA);
    }

    final var saveStamp = entityUpdate.saveStamp();
    final var entityNode = entityNodeContainer.get();
    final var saveStampNode = ENTITY_NODE_SEARCHER.getStoredSaveStampNodeFromEntityNode(entityNode);
    final var actualSaveStamp = saveStampNode.getHeader();

    if (!saveStamp.equals(actualSaveStamp)) {
      throw ChangedResourceException.forResource(PluralLowerCaseVariableCatalog.DATA);
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
    final TableViewDto tableView) {
    for (final var f : entityUpdate.updatedContentFields()) {
      final var columnName = f.columnName();
      final var columnView = TABLE_VIEW_SEARCHER.getColumnViewByColumnName(tableView, columnName);

      if (columnView.fieldType().getCardinality().getBaseCardinality() == BaseCardinality.SINGLE) {
        final var oneBasedColumnIndex = columnView.oneBasedOrdinalIndex();
        final var fieldNode = entityNode.getStoredChildNodeAtOneBasedIndex(oneBasedColumnIndex);
        final var newFieldNode = CONTENT_FIELD_NODE_MAPPER.mapStringContentFieldDtoToContentFieldNode(f);

        fieldNode.resetFromNode(newFieldNode);
      }
    }
  }
}
