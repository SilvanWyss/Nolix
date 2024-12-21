package ch.nolix.system.noderawdata.datawriter;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedInTheMeanwhileException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.noderawdata.nodesearcher.EntityNodeSearcher;
import ch.nolix.system.noderawdata.nodesearcher.TableNodeSearcher;
import ch.nolix.system.noderawdata.tabledefinition.FieldIndexCatalogue;
import ch.nolix.system.noderawschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.NodeDatabaseSearcher;
import ch.nolix.systemapi.rawdataapi.datadto.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

final class DatabaseUpdater {

  private static final NodeDatabaseSearcher DATABASE_NODE_SEARCHER = new NodeDatabaseSearcher();

  private static final DatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final EntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  private static final EntityHeadNodeMapper ENTITY_HEAD_NODE_MAPPER = new EntityHeadNodeMapper();

  private static final EntityNodeMapper ENTITY_NODE_MAPPER = new EntityNodeMapper();

  public void deleteEntriesFromMultiReference(
    final IMutableNode<?> nodeDatabase,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiReferenceColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceColumnIndex = multiReferenceColumnInfo.getColumnIndexOnEntityNode();

    final var multiReferenceColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    multiReferenceColumnNode.removeChildNodes();
  }

  public void deleteEntriesFromMultiValue(
    final IMutableNode<?> nodeDatabase,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiValueColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiValueColumnIndex = multiValueColumnInfo.getColumnIndexOnEntityNode();

    final var multiValueColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnIndex);

    multiValueColumnNode.removeChildNodes();
  }

  public void deleteEntryFromMultiReference(
    final IMutableNode<?> nodeDatabase,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiReferenceColumnInfo,
    final String referencedEntityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceColumnIndex = multiReferenceColumnInfo.getColumnIndexOnEntityNode();

    final var multiReferenceColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    multiReferenceColumnNode.removeFirstChildNodeWithHeader(referencedEntityId);
  }

  public void deleteEntryFromMultiValue(
    final IMutableNode<?> nodeDatabase,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiValueColumnInfo,
    final String entry) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiValueColumnIndex = multiValueColumnInfo.getColumnIndexOnEntityNode();

    final var multiValueColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnIndex);

    multiValueColumnNode.removeFirstChildNodeWithHeader(entry);
  }

  public void deleteEntityFromTable(
    final IMutableNode<?> database,
    final String tableName,
    final EntityDeletionDto entity) {

    deleteEntityHeadFromDatabase(database, entity);

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(database, tableName);

    final var entityNode = TABLE_NODE_SEARCHER.removeAndGetStoredEntityNodeById(tableNode, entity.id());

    final var saveStampNode = ENTITY_NODE_SEARCHER.getStoredSaveStampNodeFromEntityNode(entityNode);

    if (!saveStampNode.hasHeader(entity.saveStamp())) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }
  }

  public void deleteMultiBackReferenceEntry(
    final IMutableNode<?> nodeDatabase,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiBackReferenceColumnInfo,
    final String backReferencedEntityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiBackReferenceColumnIndex = multiBackReferenceColumnInfo.getColumnIndexOnEntityNode();

    final var multiBackReferenceColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiBackReferenceColumnIndex);

    multiBackReferenceColumnNode.removeFirstChildNodeWithHeader(backReferencedEntityId);
  }

  public void expectGivenSchemaTimestamp(final IMutableNode<?> nodeDatabase, final ITime schemaTimestamp) {

    final var databasePropertiesNode = DATABASE_NODE_SEARCHER
      .getStoredDatabasePropertiesNodeFromNodeDatabase(nodeDatabase);

    final var actualSchemaTimestamp = DATABASE_PROPERTIES_NODE_SEARCHER
      .getSchemaTimestampFromDatabasePropertiesNode(databasePropertiesNode);

    if (!actualSchemaTimestamp.equals(schemaTimestamp)) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("schema");
    }
  }

  public void expectTableContainsEntity(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    final var containsEntity = TABLE_NODE_SEARCHER.tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenValue(
      tableNode,
      FieldIndexCatalogue.ID_INDEX,
      entityId);

    if (!containsEntity) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalogue.DATABASE,
        nodeDatabase,
        "does not contain a " + tableName + " with the id " + entityId);
    }
  }

  public void insertEntityIntoTable(
    final IMutableNode<?> nodeDatabase,
    final ITableInfo tableInfo,
    final EntityCreationDto newEntity) {

    insertEntityHeadIntoDatabase(nodeDatabase, tableInfo, newEntity);

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableInfo.getTableName());

    if (TABLE_NODE_SEARCHER.tableNodeContainsEntityNodeWithGivenId(tableNode, newEntity.id())) {
      throw ArgumentHasAttributeException.forArgumentAndAttributeName(
        "table " + tableInfo.getTableNameInQuotes(),
        "entity with the id '" + newEntity.id() + "'");
    }

    final var entityNode = ENTITY_NODE_MAPPER.createNodeFromEntityWithSaveStamp(tableInfo, newEntity, 0);

    tableNode.addChildNode(entityNode);
  }

  public void insertEntryIntoMultiBackReference(
    final IMutableNode<?> nodeDatabase,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiBackReferenceColumnInfo,
    final String backReferencedEntityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromNodeDatabase(nodeDatabase,
      tableInfo.getTableId());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiBackReferenceColumnIndex = multiBackReferenceColumnInfo.getColumnIndexOnEntityNode();

    final var multiBackReferenceNode = entityNode.getStoredChildNodeAt1BasedIndex(multiBackReferenceColumnIndex);

    multiBackReferenceNode.addChildNode(Node.withHeader(backReferencedEntityId));
  }

  public void insertEntryIntoMultiReference(
    final IMutableNode<?> nodeDatabase,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiReferenceColumnInfo,
    final String referencedEntityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceColumnIndex = multiReferenceColumnInfo.getColumnIndexOnEntityNode();

    final var multiReferenceNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    multiReferenceNode.addChildNode(Node.withHeader(referencedEntityId));
  }

  public void insertEntryIntoMultiValue(
    final IMutableNode<?> nodeDatabase,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiValueColumnInfo,
    final String entry) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiValueColumnIndex = multiValueColumnInfo.getColumnIndexOnEntityNode();

    final var multiValueNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnIndex);

    multiValueNode.addChildNode(Node.withHeader(entry));
  }

  public void updateEntityOnTable(
    final IMutableNode<?> database,
    final ITableInfo tableInfo,
    final EntityUpdateDto entityUpdate) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(database,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getOptionalStoredEntityNodeFromTableNode(tableNode,
      entityUpdate.id());

    if (entityNode.isEmpty()) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }

    final var saveStampNode = ENTITY_NODE_SEARCHER.getStoredSaveStampNodeFromEntityNode(entityNode.get());

    final var saveStamp = saveStampNode.getHeader();
    if (!saveStamp.equals(entityUpdate.saveStamp())) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }

    final var newSaveStamp = String.valueOf(Integer.valueOf(saveStamp) + 1);
    saveStampNode.setHeader(newSaveStamp);

    updateEntityNode(entityNode.get(), tableInfo, entityUpdate);
  }

  private void deleteEntityHeadFromDatabase(final IMutableNode<?> nodeDatabase, final EntityDeletionDto entity) {

    final var entityId = entity.id();
    final var entityHeadsNode = DATABASE_NODE_SEARCHER.getStoredEntityHeadsNodeFromNodeDatabase(nodeDatabase);

    entityHeadsNode.removeFirstChildNodeThat(ehn -> ehn.getStoredChildNodeAt1BasedIndex(2).hasHeader(entityId));
  }

  private void insertEntityHeadIntoDatabase(
    final IMutableNode<?> nodeDatabase,
    final ITableInfo tableInfo,
    final EntityCreationDto newEntity) {

    final var entityHeadsNode = DATABASE_NODE_SEARCHER.getStoredEntityHeadsNodeFromNodeDatabase(nodeDatabase);

    final var entityHeadNode = ENTITY_HEAD_NODE_MAPPER.createEntityHeadNode(tableInfo, newEntity);

    entityHeadsNode.addChildNode(entityHeadNode);
  }

  private void updateEntityNode(
    final IMutableNode<?> entityNode,
    final ITableInfo tableInfo,
    final EntityUpdateDto entityUpdate) {
    for (final var f : entityUpdate.updatedContentFields()) {

      final var columnInfo = tableInfo.getColumnInfoByColumnName(f.columnName());
      final var columnIndex = columnInfo.getColumnIndexOnEntityNode();
      final var contentFieldNode = entityNode.getStoredChildNodeAt1BasedIndex(columnIndex);

      final var valueAsString = f.optionalContent();
      if (valueAsString.isEmpty()) {
        contentFieldNode.removeHeader();
      } else {
        contentFieldNode.setHeader(valueAsString.get());
      }
    }
  }

}
