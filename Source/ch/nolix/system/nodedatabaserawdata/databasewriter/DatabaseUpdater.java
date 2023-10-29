//package declaration
package ch.nolix.system.nodedatabaserawdata.databasewriter;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedInTheMeanwhileException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.structure.EntityNodeSearcher;
import ch.nolix.system.nodedatabaserawdata.structure.TableNodeSearcher;
import ch.nolix.system.nodedatabaserawdata.tabledefinition.FieldIndexCatalogue;
import ch.nolix.system.nodedatabaserawschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDto;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
final class DatabaseUpdater {

  //constant
  private static final DatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  //constant
  private static final DatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  //constant
  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  //constant
  private static final EntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  //constant
  private static final EntityNodeMapper ENTITY_NODE_MAPPER = new EntityNodeMapper();

  //method
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

  //method
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

  //method
  public void deleteEntryFromMultiReference(
    final IMutableNode<?> databaseNode,
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiReferencedColumnInfo,
    final String referencedEntityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceColumnIndex = multiReferencedColumnInfo.getColumnIndexOnEntityNode();

    final var multiReferenceColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    multiReferenceColumnNode.removeFirstChildNodeWithHeader(referencedEntityId);
  }

  //method
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

  //method
  public void deleteEntityFromTable(
    final IMutableNode<?> database,
    final String tableName,
    final IEntityHeadDto entity) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(database, tableName);

    final var entityNode = TABLE_NODE_SEARCHER.removeAndGetRefEntityNodeFromTableNode(tableNode, entity.getId());

    final var saveStampNode = ENTITY_NODE_SEARCHER.getStoredSaveStampNodeFromEntityNode(entityNode);

    if (!saveStampNode.hasHeader(entity.getSaveStamp())) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }
  }

  //method
  public void expectGivenSchemaTimestamp(final IMutableNode<?> databaseNode, final ITime schemaTimestamp) {

    final var databasePropertiesNode = DATABASE_NODE_SEARCHER
      .getStoredDatabasePropertiesNodeFromDatabaseNode(databaseNode);

    final var actualSchemaTimestamp = DATABASE_PROPERTIES_NODE_SEARCHER
      .getSchemaTimestampFromDatabasePropertiesNode(databasePropertiesNode);

    if (!actualSchemaTimestamp.equals(schemaTimestamp)) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("schema");
    }
  }

  //method
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
        LowerCaseCatalogue.DATABASE,
        databaseNode,
        "does not contain a " + tableName + " with the id " + entityId);
    }
  }

  //method
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

    final var multiReferenceColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    multiReferenceColumnNode.addChildNode(Node.withHeader(referencedEntityId));
  }

  //method
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

    final var multiValueColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnIndex);

    multiValueColumnNode.addChildNode(Node.withHeader(entry));
  }

  //method
  public void insertEntityIntoTable(
    final IMutableNode<?> database,
    final ITableInfo tableInfo,
    final INewEntityDto newEntity) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(database,
      tableInfo.getTableName());

    if (TABLE_NODE_SEARCHER.tableNodeContainsEntityNodeWithGivenId(tableNode, newEntity.getId())) {
      throw ArgumentHasAttributeException.forArgumentAndAttributeName(
        "table " + tableInfo.getTableNameInQuotes(),
        "entity with the id '" + newEntity.getId() + "'");
    }

    final var entityNode = ENTITY_NODE_MAPPER.createNodeFromEntityWithSaveStamp(tableInfo, newEntity, 0);

    tableNode.addChildNode(entityNode);
  }

  //method
  public void setEntityAsUpdated(final IMutableNode<?> database, final String tableName, final IEntityHeadDto entity) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(database, tableName);

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNodeOrNull(tableNode, entity.getId());
    if (entityNode == null) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }

    final var saveStampNode = ENTITY_NODE_SEARCHER.getStoredSaveStampNodeFromEntityNode(entityNode);

    final var saveStamp = saveStampNode.getHeader();
    if (!saveStamp.equals(entity.getSaveStamp())) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }

    final var newSaveStamp = String.valueOf(Integer.valueOf(saveStamp) + 1);
    saveStampNode.setHeader(newSaveStamp);
  }

  //method
  public void updateEntityOnTable(
    final IMutableNode<?> database,
    final ITableInfo tableInfo,
    final IEntityUpdateDto entityUpdate) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(database,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNodeOrNull(tableNode, entityUpdate.getId());
    if (entityNode == null) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }

    final var saveStampNode = ENTITY_NODE_SEARCHER.getStoredSaveStampNodeFromEntityNode(entityNode);

    final var saveStamp = saveStampNode.getHeader();
    if (!saveStamp.equals(entityUpdate.getSaveStamp())) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }

    final var newSaveStamp = String.valueOf(Integer.valueOf(saveStamp) + 1);
    saveStampNode.setHeader(newSaveStamp);

    updateEntityNode(entityNode, tableInfo, entityUpdate);
  }

  //method
  private void updateEntityNode(
    final IMutableNode<?> entityNode,
    final ITableInfo tableInfo,
    final IEntityUpdateDto entityUpdate) {
    for (final var ucf : entityUpdate.getUpdatedContentFields()) {

      final var columnInfo = tableInfo.getColumnInfoByColumnName(ucf.getColumnName());
      final var columnIndex = columnInfo.getColumnIndexOnEntityNode();
      final var contentFieldNode = entityNode.getStoredChildNodeAt1BasedIndex(columnIndex);

      final var value = ucf.getValueAsStringOrNull();
      if (value == null) {
        contentFieldNode.removeHeader();
      } else {
        contentFieldNode.setHeader(value);
      }
    }
  }
}
