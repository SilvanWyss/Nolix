package ch.nolix.system.noderawdata.datawriter;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedInTheMeanwhileException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.noderawdata.nodeeditor.TableNodeEditor;
import ch.nolix.system.noderawdata.nodeexaminer.TableNodeExaminer;
import ch.nolix.system.noderawdata.nodemapper.EntityIndexNodeMapper;
import ch.nolix.system.noderawdata.nodemapper.EntityNodeMapper;
import ch.nolix.system.noderawdata.nodesearcher.EntityNodeSearcher;
import ch.nolix.system.noderawdata.nodesearcher.TableNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.system.rawdata.schemaviewdtosearcher.TableViewDtoSearcher;
import ch.nolix.systemapi.noderawdataapi.nodeeditorapi.ITableNodeEditor;
import ch.nolix.systemapi.noderawdataapi.nodeexaminerapi.ITableNodeExaminer;
import ch.nolix.systemapi.noderawdataapi.nodemapperapi.IEntityIndexNodeMapper;
import ch.nolix.systemapi.noderawdataapi.nodemapperapi.IEntityNodeMapper;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.IEntityNodeSearcher;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.ITableNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.FieldIndexCatalog;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabaseNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableViewDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DatabaseUpdater {

  private static final IDatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final IDatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final ITableViewDtoSearcher TABLE_VIEW_DTO_SEARCHER = new TableViewDtoSearcher();

  private static final ITableNodeEditor TABLE_NODE_EDITOR = new TableNodeEditor();

  private static final ITableNodeExaminer TABLE_NODE_EXAMINER = new TableNodeExaminer();

  private static final IEntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  private static final IEntityIndexNodeMapper ENTITY_INDEXES_NODE_MAPPER = new EntityIndexNodeMapper();

  private static final IEntityNodeMapper ENTITY_NODE_MAPPER = new EntityNodeMapper();

  public void deleteEntriesFromMultiReference(
    final IMutableNode<?> nodeDatabase,
    final TableViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiReferenceColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceColumnIndex = multiReferenceColumnInfo.oneBasedOrdinalIndex();

    final var multiReferenceColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    multiReferenceColumnNode.removeChildNodes();
  }

  public void deleteEntriesFromMultiValue(
    final IMutableNode<?> nodeDatabase,
    final TableViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiValueColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiValueColumnIndex = multiValueColumnInfo.oneBasedOrdinalIndex();

    final var multiValueColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnIndex);

    multiValueColumnNode.removeChildNodes();
  }

  public void deleteEntryFromMultiReference(
    final IMutableNode<?> nodeDatabase,
    final TableViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiReferenceColumnInfo,
    final String referencedEntityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceColumnIndex = multiReferenceColumnInfo.oneBasedOrdinalIndex();

    final var multiReferenceColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    multiReferenceColumnNode.removeFirstChildNodeWithHeader(referencedEntityId);
  }

  public void deleteEntryFromMultiValue(
    final IMutableNode<?> nodeDatabase,
    final TableViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiValueColumnInfo,
    final String entry) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiValueColumnIndex = multiValueColumnInfo.oneBasedOrdinalIndex();

    final var multiValueColumnNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnIndex);

    multiValueColumnNode.removeFirstChildNodeWithHeader(entry);
  }

  public void deleteEntityFromTable(
    final IMutableNode<?> database,
    final String tableName,
    final EntityDeletionDto entity) {

    deleteEntityIndexFromDatabase(database, entity);

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(database, tableName);

    final var entityNode = TABLE_NODE_EDITOR.removeAndGetStoredEntityNodeById(tableNode, entity.id());

    final var saveStampNode = ENTITY_NODE_SEARCHER.getStoredSaveStampNodeFromEntityNode(entityNode);

    if (!saveStampNode.hasHeader(entity.saveStamp())) {
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }
  }

  public void deleteMultiBackReferenceEntry(
    final IMutableNode<?> nodeDatabase,
    final TableViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiBackReferenceColumnInfo,
    final String backReferencedEntityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiBackReferenceColumnIndex = multiBackReferenceColumnInfo.oneBasedOrdinalIndex();

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

    final var containsEntity = TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenValue(
      tableNode,
      FieldIndexCatalog.ID_INDEX,
      entityId);

    if (!containsEntity) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalog.DATABASE,
        nodeDatabase,
        "does not contain a " + tableName + " with the id " + entityId);
    }
  }

  public void insertEntityIntoTable(
    final IMutableNode<?> nodeDatabase,
    final TableViewDto tableView,
    final EntityCreationDto newEntity) {

    insertEntityIndexIntoDatabase(nodeDatabase, tableView, newEntity);

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    if (TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWithGivenId(tableNode, newEntity.id())) {
      throw ArgumentHasAttributeException.forArgumentAndAttributeName(
        tableNode,
        "entity with the id '" + newEntity.id() + "'");
    }

    final var entityNode = ENTITY_NODE_MAPPER.mapEntityCreationDtoToEntityNode(newEntity, tableView, 0);

    tableNode.addChildNode(entityNode);
  }

  public void insertEntryIntoMultiBackReference(
    final IMutableNode<?> nodeDatabase,
    final TableViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiBackReferenceColumnInfo,
    final String backReferencedEntityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromNodeDatabase(nodeDatabase,
      tableView.id());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiBackReferenceColumnIndex = multiBackReferenceColumnInfo.oneBasedOrdinalIndex();

    final var multiBackReferenceNode = entityNode.getStoredChildNodeAt1BasedIndex(multiBackReferenceColumnIndex);

    multiBackReferenceNode.addChildNode(Node.withHeader(backReferencedEntityId));
  }

  public void insertEntryIntoMultiReference(
    final IMutableNode<?> nodeDatabase,
    final TableViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiReferenceColumnInfo,
    final String referencedEntityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceColumnIndex = multiReferenceColumnInfo.oneBasedOrdinalIndex();

    final var multiReferenceNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    multiReferenceNode.addChildNode(Node.withHeader(referencedEntityId));
  }

  public void insertEntryIntoMultiValue(
    final IMutableNode<?> nodeDatabase,
    final TableViewDto tableView,
    final String entityId,
    final ColumnSchemaViewDto multiValueColumnInfo,
    final String entry) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiValueColumnIndex = multiValueColumnInfo.oneBasedOrdinalIndex();

    final var multiValueNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnIndex);

    multiValueNode.addChildNode(Node.withHeader(entry));
  }

  public void updateEntityOnTable(
    final IMutableNode<?> database,
    final TableViewDto tableView,
    final EntityUpdateDto entityUpdate) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(database,
      tableView.name());

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

    updateEntityNode(entityNode.get(), tableView, entityUpdate);
  }

  private void deleteEntityIndexFromDatabase(final IMutableNode<?> nodeDatabase, final EntityDeletionDto entity) {

    final var entityId = entity.id();
    final var entityIndexesNode = DATABASE_NODE_SEARCHER.getStoredEntityIndexesNodeFromNodeDatabase(nodeDatabase);

    entityIndexesNode.removeFirstChildNodeThat(ehn -> ehn.getStoredChildNodeAt1BasedIndex(2).hasHeader(entityId));
  }

  private void insertEntityIndexIntoDatabase(
    final IMutableNode<?> nodeDatabase,
    final TableViewDto tableView,
    final EntityCreationDto newEntity) {

    final var entityIndexesNode = DATABASE_NODE_SEARCHER.getStoredEntityIndexesNodeFromNodeDatabase(nodeDatabase);
    final var entityIndexNode = ENTITY_INDEXES_NODE_MAPPER.mapEntityCreationDtoToEntityIndexNode(newEntity, tableView);

    entityIndexesNode.addChildNode(entityIndexNode);
  }

  private void updateEntityNode(
    final IMutableNode<?> entityNode,
    final TableViewDto tableView,
    final EntityUpdateDto entityUpdate) {
    for (final var f : entityUpdate.updatedContentFields()) {

      final var columnInfo = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableView, f.columnName());
      final var columnIndex = columnInfo.oneBasedOrdinalIndex();
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
