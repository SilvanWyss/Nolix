package ch.nolix.system.noderawdata.datawriter;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.ResourceWasChangedInTheMeanwhileException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.nodemidschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.system.nodemidschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.system.noderawdata.nodeeditor.TableNodeEditor;
import ch.nolix.system.noderawdata.nodeexaminer.TableNodeExaminer;
import ch.nolix.system.noderawdata.nodemapper.ContentFieldNodeMapper;
import ch.nolix.system.noderawdata.nodemapper.EntityIndexNodeMapper;
import ch.nolix.system.noderawdata.nodemapper.EntityNodeMapper;
import ch.nolix.system.noderawdata.nodesearcher.EntityNodeSearcher;
import ch.nolix.system.noderawdata.nodesearcher.TableNodeSearcher;
import ch.nolix.system.noderawdata.nodevalidator.TableNodeValidator;
import ch.nolix.system.rawdata.schemaviewdtosearcher.TableViewDtoSearcher;
import ch.nolix.systemapi.nodemidschemaapi.nodesearcherapi.IDatabaseNodeSearcher;
import ch.nolix.systemapi.nodemidschemaapi.nodesearcherapi.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.noderawdataapi.nodeeditorapi.ITableNodeEditor;
import ch.nolix.systemapi.noderawdataapi.nodeexaminerapi.ITableNodeExaminer;
import ch.nolix.systemapi.noderawdataapi.nodemapperapi.IContentFieldNodeMapper;
import ch.nolix.systemapi.noderawdataapi.nodemapperapi.IEntityIndexNodeMapper;
import ch.nolix.systemapi.noderawdataapi.nodemapperapi.IEntityNodeMapper;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.IEntityNodeSearcher;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.ITableNodeSearcher;
import ch.nolix.systemapi.noderawdataapi.nodevalidatorapi.ITableNodeValidator;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DataWriterActionProvider {

  private static final IDatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final IDatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final ITableViewDtoSearcher TABLE_VIEW_DTO_SEARCHER = new TableViewDtoSearcher();

  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final ITableNodeExaminer TABLE_NODE_EXAMINER = new TableNodeExaminer();

  private static final ITableNodeValidator TABLE_NODE_VALIDATOR = new TableNodeValidator();

  private static final ITableNodeEditor TABLE_NODE_EDITOR = new TableNodeEditor();

  private static final IEntityIndexNodeMapper ENTITY_INDEXES_NODE_MAPPER = new EntityIndexNodeMapper();

  private static final IEntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  private static final IEntityNodeMapper ENTITY_NODE_MAPPER = new EntityNodeMapper();

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
    entityNode.getStoredChildNodeAt1BasedIndex(multiReferencedColumnOneBasedOrdinalIndex);

    multiReferenceNode.reset();
  }

  public static void clearMultiValue(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiValueColumnOneBasedOrdinalIndex) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);
    final var multiValueNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnOneBasedOrdinalIndex);

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
      throw ResourceWasChangedInTheMeanwhileException.forResource("data");
    }
  }

  public static void deleteMultiBackReferenceEntry(
    final IMutableNode<?> nodeDatabase,
    final TableSchemaViewDto tableView,
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

  public static void deleteMultiReferenceEntry(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiReferencedColumnOneBasedOrdinalIndex,
    final String referencedEntityId) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceNode = //
    entityNode.getStoredChildNodeAt1BasedIndex(multiReferencedColumnOneBasedOrdinalIndex);

    multiReferenceNode.removeFirstChildNodeWithHeader(referencedEntityId);
  }

  public static void deleteMultiValueEntry(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String entityId,
    final int multiValueColumnOneBasedOrdinalIndex,
    final String entry) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);
    final var multiValueNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnOneBasedOrdinalIndex);

    multiValueNode.removeFirstChildNodeWithHeader(entry);
  }

  public static void expectSchemaTimestamp(final IMutableNode<?> nodeDatabase, final ITime schemaTimestamp) {

    final var databasePropertiesNode = //
    DATABASE_NODE_SEARCHER.getStoredDatabasePropertiesNodeFromNodeDatabase(nodeDatabase);

    final var actualSchemaTimestamp = //
    DATABASE_PROPERTIES_NODE_SEARCHER.getSchemaTimestampFromDatabasePropertiesNode(databasePropertiesNode);

    if (!actualSchemaTimestamp.equals(schemaTimestamp)) {
      throw ResourceWasChangedInTheMeanwhileException.forResource(LowerCaseVariableCatalog.SCHEMA);
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
    final TableSchemaViewDto tableView,
    final EntityCreationDto newEntity) {

    insertEntityIndex(nodeDatabase, tableView.id(), newEntity);

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

  public static void insertMultiBackReferenceEntry(
    final IMutableNode<?> nodeDatabase,
    final TableSchemaViewDto tableView,
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

  public static void insertMultiReferenceEntry(
    final IMutableNode<?> nodeDatabase,
    final MultiReferenceEntryDto multiReferenceEntry,
    final int multiReferenceColumnOneBasedOrdinalIndex) {

    final var tableName = multiReferenceEntry.tableName();
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityId = multiReferenceEntry.entityid();
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);
    final var multiReferenceNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnOneBasedOrdinalIndex);
    final var multiReferenceEntryNode = Node.withHeader(multiReferenceEntry.referencedEntityId());

    multiReferenceNode.addChildNode(multiReferenceEntryNode);
  }

  public static void insertMultiValueEntry(
    final IMutableNode<?> nodeDatabase,
    final TableSchemaViewDto tableView,
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

  public static void updateEntity(
    final IMutableNode<?> database,
    final TableSchemaViewDto tableView,
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

  private static void deleteEntityIndex(final IMutableNode<?> nodeDatabase, final String entityId) {

    final var entityIndexesNode = DATABASE_NODE_SEARCHER.getStoredEntityIndexesNodeFromNodeDatabase(nodeDatabase);

    entityIndexesNode.removeFirstChildNodeThat(ehn -> ehn.getStoredChildNodeAt1BasedIndex(2).hasHeader(entityId));
  }

  private static void insertEntityIndex(
    final IMutableNode<?> nodeDatabase,
    final String tableId,
    final EntityCreationDto newEntity) {

    final var entityIndexesNode = DATABASE_NODE_SEARCHER.getStoredEntityIndexesNodeFromNodeDatabase(nodeDatabase);
    final var entityIndexNode = ENTITY_INDEXES_NODE_MAPPER.mapEntityCreationDtoToEntityIndexNode(newEntity, tableId);

    entityIndexesNode.addChildNode(entityIndexNode);
  }

  private static void updateEntityNode(
    final IMutableNode<?> entityNode,
    final TableSchemaViewDto tableView,
    final EntityUpdateDto entityUpdate) {
    for (final var f : entityUpdate.updatedContentFields()) {

      final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableView, f.columnName());

      if (columnView.contentType().getCardinality() != Cardinality.TO_MANY) {

        final var columnIndex = columnView.oneBasedOrdinalIndex();
        final var contentFieldNode = entityNode.getStoredChildNodeAt1BasedIndex(columnIndex);
        final var newContentFieldNode = CONTENT_FIELD_NODE_MAPPER.mapStringContentFieldDtoToContentFieldNode(f);

        contentFieldNode.resetFromNode(newContentFieldNode);
      }
    }
  }
}
