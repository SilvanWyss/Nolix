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
import ch.nolix.system.noderawschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.INewEntityDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

final class DatabaseUpdater {

  private static final DatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final DatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final EntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  private static final EntityHeadNodeMapper ENTITY_HEAD_NODE_MAPPER = new EntityHeadNodeMapper();

  private static final EntityNodeMapper ENTITY_NODE_MAPPER = new EntityNodeMapper();

  public void deleteEntriesFromMultiReference(
    final IMutableNode<?> databaseNode,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiReferenceColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceColumnIndex = multiReferenceColumnInfo.getColumnIndexOnEntityNode();

    final var multiReferenceColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    multiReferenceColumnNode.removeChildNodes();
  }

  public void deleteEntriesFromMultiValue(
    final IMutableNode<?> databaseNode,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiValueColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiValueColumnIndex = multiValueColumnInfo.getColumnIndexOnEntityNode();

    final var multiValueColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnIndex);

    multiValueColumnNode.removeChildNodes();
  }

  public void deleteEntryFromMultiReference(
    final IMutableNode<?> databaseNode,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiReferenceColumnInfo,
    final String referencedEntityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceColumnIndex = multiReferenceColumnInfo.getColumnIndexOnEntityNode();

    final var multiReferenceColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    multiReferenceColumnNode.removeFirstChildNodeWithHeader(referencedEntityId);
  }

  public void deleteEntryFromMultiValue(
    final IMutableNode<?> databaseNode,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiValueColumnInfo,
    final String entry) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiValueColumnIndex = multiValueColumnInfo.getColumnIndexOnEntityNode();

    final var multiValueColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnIndex);

    multiValueColumnNode.removeFirstChildNodeWithHeader(entry);
  }

  public void deleteEntityFromTable(
    final IMutableNode<?> database,
    final String tableName,
    final IEntityHeadDto entity) {

    deleteEntityHeadFromDatabase(database, entity);

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(database, tableName);

    final var entityNode = TABLE_NODE_SEARCHER.removeAndGetRefEntityNodeFromTableNode(tableNode, entity.getId());

    final var saveStampNode = ENTITY_NODE_SEARCHER.getStoredSaveStampNodeFromEntityNode(entityNode);

    if (!saveStampNode.hasHeader(entity.getSaveStamp())) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }
  }

  public void deleteMultiBackReferenceEntry(
    final IMutableNode<?> databaseNode,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiBackReferenceColumnInfo,
    final String backReferencedEntityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiBackReferenceColumnIndex = multiBackReferenceColumnInfo.getColumnIndexOnEntityNode();

    final var multiBackReferenceColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiBackReferenceColumnIndex);

    multiBackReferenceColumnNode.removeFirstChildNodeWithHeader(backReferencedEntityId);
  }

  public void expectGivenSchemaTimestamp(final IMutableNode<?> databaseNode, final ITime schemaTimestamp) {

    final var databasePropertiesNode = DATABASE_NODE_SEARCHER
      .getStoredDatabasePropertiesNodeFromDatabaseNode(databaseNode);

    final var actualSchemaTimestamp = DATABASE_PROPERTIES_NODE_SEARCHER
      .getSchemaTimestampFromDatabasePropertiesNode(databasePropertiesNode);

    if (!actualSchemaTimestamp.equals(schemaTimestamp)) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("schema");
    }
  }

  public void expectTableContainsEntity(
    final IMutableNode<?> databaseNode,
    final String tableName,
    final String entityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode, tableName);

    final var containsEntity = TABLE_NODE_SEARCHER.tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenValue(
      tableNode,
      FieldIndexCatalogue.ID_INDEX,
      entityId);

    if (!containsEntity) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalogue.DATABASE,
        databaseNode,
        "does not contain a " + tableName + " with the id " + entityId);
    }
  }

  public void insertEntityIntoTable(
    final IMutableNode<?> databaseNode,
    final ITableInfo tableInfo,
    final INewEntityDto newEntity) {

    insertEntityHeadIntoDatabase(databaseNode, tableInfo, newEntity);

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode,
      tableInfo.getTableName());

    if (TABLE_NODE_SEARCHER.tableNodeContainsEntityNodeWithGivenId(tableNode, newEntity.getId())) {
      throw ArgumentHasAttributeException.forArgumentAndAttributeName(
        "table " + tableInfo.getTableNameInQuotes(),
        "entity with the id '" + newEntity.getId() + "'");
    }

    final var entityNode = ENTITY_NODE_MAPPER.createNodeFromEntityWithSaveStamp(tableInfo, newEntity, 0);

    tableNode.addChildNode(entityNode);
  }

  public void insertEntryIntoMultiBackReference(
    final IMutableNode<?> databaseNode,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiBackReferenceColumnInfo,
    final String backReferencedEntityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromDatabaseNode(databaseNode,
      tableInfo.getTableId());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiBackReferenceColumnIndex = multiBackReferenceColumnInfo.getColumnIndexOnEntityNode();

    final var multiBackReferenceNode = entityNode.getStoredChildNodeAt1BasedIndex(multiBackReferenceColumnIndex);

    multiBackReferenceNode.addChildNode(Node.withHeader(backReferencedEntityId));
  }

  public void insertEntryIntoMultiReference(
    final IMutableNode<?> databaseNode,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiReferenceColumnInfo,
    final String referencedEntityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceColumnIndex = multiReferenceColumnInfo.getColumnIndexOnEntityNode();

    final var multiReferenceNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    multiReferenceNode.addChildNode(Node.withHeader(referencedEntityId));
  }

  public void insertEntryIntoMultiValue(
    final IMutableNode<?> databaseNode,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiValueColumnInfo,
    final String entry) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiValueColumnIndex = multiValueColumnInfo.getColumnIndexOnEntityNode();

    final var multiValueNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnIndex);

    multiValueNode.addChildNode(Node.withHeader(entry));
  }

  public void setEntityAsUpdated(final IMutableNode<?> database, final String tableName, final IEntityHeadDto entity) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(database, tableName);

    final var entityNode = TABLE_NODE_SEARCHER.getOptionalStoredEntityNodeFromTableNode(tableNode, entity.getId());

    if (entityNode.isEmpty()) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }

    final var saveStampNode = ENTITY_NODE_SEARCHER.getStoredSaveStampNodeFromEntityNode(entityNode.get());

    final var saveStamp = saveStampNode.getHeader();
    if (!saveStamp.equals(entity.getSaveStamp())) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }

    final var newSaveStamp = String.valueOf(Integer.valueOf(saveStamp) + 1);
    saveStampNode.setHeader(newSaveStamp);
  }

  public void updateEntityOnTable(
    final IMutableNode<?> database,
    final ITableInfo tableInfo,
    final IEntityUpdateDto entityUpdate) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(database,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getOptionalStoredEntityNodeFromTableNode(tableNode,
      entityUpdate.getId());

    if (entityNode.isEmpty()) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }

    final var saveStampNode = ENTITY_NODE_SEARCHER.getStoredSaveStampNodeFromEntityNode(entityNode.get());

    final var saveStamp = saveStampNode.getHeader();
    if (!saveStamp.equals(entityUpdate.getSaveStamp())) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }

    final var newSaveStamp = String.valueOf(Integer.valueOf(saveStamp) + 1);
    saveStampNode.setHeader(newSaveStamp);

    updateEntityNode(entityNode.get(), tableInfo, entityUpdate);
  }

  private void deleteEntityHeadFromDatabase(final IMutableNode<?> databaseNode, final IEntityHeadDto entity) {

    final var entityId = entity.getId();
    final var entityHeadsNode = DATABASE_NODE_SEARCHER.getStoredEntityHeadsNodeFromDatabaseNode(databaseNode);

    entityHeadsNode.removeFirstChildNodeThat(ehn -> ehn.getStoredChildNodeAt1BasedIndex(2).hasHeader(entityId));
  }

  private void insertEntityHeadIntoDatabase(
    final IMutableNode<?> databaseNode,
    final ITableInfo tableInfo,
    final INewEntityDto newEntity) {

    final var entityHeadsNode = DATABASE_NODE_SEARCHER.getStoredEntityHeadsNodeFromDatabaseNode(databaseNode);

    final var entityHeadNode = ENTITY_HEAD_NODE_MAPPER.createEntityHeadNode(tableInfo, newEntity);

    entityHeadsNode.addChildNode(entityHeadNode);
  }

  private void updateEntityNode(
    final IMutableNode<?> entityNode,
    final ITableInfo tableInfo,
    final IEntityUpdateDto entityUpdate) {
    for (final var ucf : entityUpdate.getUpdatedContentFields()) {

      final var columnInfo = tableInfo.getColumnInfoByColumnName(ucf.getColumnName());
      final var columnIndex = columnInfo.getColumnIndexOnEntityNode();
      final var contentFieldNode = entityNode.getStoredChildNodeAt1BasedIndex(columnIndex);

      final var valueAsString = ucf.getOptionalValueAsString();
      if (valueAsString.isEmpty()) {
        contentFieldNode.removeHeader();
      } else {
        contentFieldNode.setHeader(valueAsString.get());
      }
    }
  }

}
