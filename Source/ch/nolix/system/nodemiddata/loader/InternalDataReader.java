/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.loader;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.system.middata.valuemapper.ValueMapper;
import ch.nolix.system.nodemiddata.modelmapper.EntityLoadingDtoMapper;
import ch.nolix.system.nodemiddata.modelmapper.MultiBackReferenceEntryDtoMapper;
import ch.nolix.system.nodemiddata.nodeexaminer.TableNodeExaminer;
import ch.nolix.system.nodemiddata.nodesearcher.TableNodeSearcher;
import ch.nolix.system.nodemidschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.system.nodemidschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDto;
import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.time.main.ITime;

/**
 * @author Silvan Wyss
 */
public final class InternalDataReader {
  private static final DatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final DatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final TableNodeExaminer TABLE_NODE_EXAMINER = new TableNodeExaminer();

  private static final EntityLoadingDtoMapper ENTITY_LOADING_DTO_MAPPER = new EntityLoadingDtoMapper();

  private static final MultiBackReferenceEntryDtoMapper MULTI_BACK_REFERENCE_ENTRY_DTO_MAPPER = //
  new MultiBackReferenceEntryDtoMapper();

  private static final ValueMapper VALUE_MAPPER = new ValueMapper();

  private final IMutableNode<?> nodeDatabase;

  private InternalDataReader(final IMutableNode<?> nodeDatabase) {
    Validator.assertThat(nodeDatabase).thatIsNamed("database node").isNotNull();

    this.nodeDatabase = nodeDatabase;
  }

  public static InternalDataReader forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new InternalDataReader(nodeDatabase);
  }

  public String getDatabaseName() {
    return DATABASE_NODE_SEARCHER.getDatabaseNameFromNodeDatabase(nodeDatabase);
  }

  public int getEntityCount(String tableName) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    return TABLE_NODE_SEARCHER.getEntityNodeCountOfTableNode(tableNode);
  }

  public ITime getSchemaTimestamp() {
    final var databasePropertiesNode = DATABASE_NODE_SEARCHER
      .getStoredDatabasePropertiesNodeFromNodeDatabase(nodeDatabase);

    return DATABASE_PROPERTIES_NODE_SEARCHER.getSchemaTimestampFromDatabasePropertiesNode(databasePropertiesNode);
  }

  public ExtendedIterable<EntityLoadingDto> loadEntitiesOfTable(final TableInfoDto tableView) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    return TABLE_NODE_SEARCHER
      .getStoredEntityNodesFromTableNode(tableNode)
      .to(rn -> ENTITY_LOADING_DTO_MAPPER.mapEntityNodeToEntityLoadingDto(rn, tableView));
  }

  public ExtendedIterable<String> loadMultiBackReferenceBackReferencedEntityIds(
    final String tableName,
    final String entityId,
    final ColumnInfoDto multiBackReferenceColumnView) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);
    final var multiBackReferenceColumnOneBasedOrdinalIndex = multiBackReferenceColumnView.oneBasedOrdinalIndex();

    final var multiBackReferenceNode = //
    entityNode.getStoredChildNodeAtOneBasedIndex(multiBackReferenceColumnOneBasedOrdinalIndex);

    final var multiBackReferenceBackReferencedEntityNodes = multiBackReferenceNode.getStoredChildNodes();

    return multiBackReferenceBackReferencedEntityNodes.to(b -> b.getStoredFirstChildNode().getHeader());
  }

  public ExtendedIterable<MultiBackReferenceEntryDto> loadMultiBackReferenceEntries(
    final String tableName,
    final String entityId,
    final ColumnInfoDto multiBackReferenceColumn) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);
    final var oneBasedMultiBackReferenceColumnOrdinalIndex = multiBackReferenceColumn.oneBasedOrdinalIndex();
    final var multiBackReferenceColumnId = multiBackReferenceColumn.id();

    final var multiBackReferenceNode = //
    entityNode.getStoredChildNodeAtOneBasedIndex(oneBasedMultiBackReferenceColumnOrdinalIndex);

    return //
    MULTI_BACK_REFERENCE_ENTRY_DTO_MAPPER.mapMultiBackReferenceNodeToMultiBackReferenceEntryDtos(
      tableName,
      entityId,
      multiBackReferenceColumnId,
      multiBackReferenceNode);
  }

  public ExtendedIterable<MultiReferenceEntryDto> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final ColumnInfoDto multiReferenceColumnView) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);
    final var multiReferenceColumnOneBasedOrdinalIndex = multiReferenceColumnView.oneBasedOrdinalIndex();
    final var multiReferenceColumnId = multiReferenceColumnView.id();

    final var multiReferenceNode = //
    entityNode.getStoredChildNodeAtOneBasedIndex(multiReferenceColumnOneBasedOrdinalIndex);

    final var multiReferenceEntryNodes = multiReferenceNode.getStoredChildNodes();

    return //
    multiReferenceEntryNodes
      .to( //
        n -> //
        new MultiReferenceEntryDto(
          tableName,
          entityId,
          multiReferenceColumnId,
          n.getStoredChildNodeAtOneBasedIndex(1).getHeader(),
          n.getStoredChildNodeAtOneBasedIndex(2).getHeader()));
  }

  public ExtendedIterable<Object> loadMultiValueEntries(
    final String tableName,
    final String entityId,
    final ColumnInfoDto multiValueColumnView) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);
    final var multiValueColumnOneBasedOrdinalIndex = multiValueColumnView.oneBasedOrdinalIndex();
    final var multiValueNode = entityNode.getStoredChildNodeAtOneBasedIndex(multiValueColumnOneBasedOrdinalIndex);

    return multiValueNode
      .getStoredChildNodes()
      .to(a -> VALUE_MAPPER.mapStringToValue(a.getHeader(), multiValueColumnView.dataType()));
  }

  public EntityLoadingDto loadEntity(final TableInfoDto tableView, final String id) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, id);

    return ENTITY_LOADING_DTO_MAPPER.mapEntityNodeToEntityLoadingDto(entityNode, tableView);
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final String tableName,
    final ColumnInfoDto columnView,
    final String value) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var columnOneBasedOrdinalIndex = columnView.oneBasedOrdinalIndex();

    return //
    TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenValue(
      tableNode,
      columnOneBasedOrdinalIndex,
      value);
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final String tableName,
    final ColumnInfoDto columnView,
    final String value,
    final ExtendedIterable<String> entitiesToIgnoreIds) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var columnOneBasedOrdinalIndex = columnView.oneBasedOrdinalIndex();

    return //
    TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWithFieldAtGivenOneBasedIndexWithGivenValueIgnoringGivenEntities(
      tableNode,
      columnOneBasedOrdinalIndex,
      value,
      entitiesToIgnoreIds);
  }

  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    return TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWithGivenId(tableNode, id);
  }
}
