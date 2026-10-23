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

  /**
   * @param nodeDatabase
   * @return a new {@link InternalDataReader} for the given nodeDatabase
   */
  public static InternalDataReader forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new InternalDataReader(nodeDatabase);
  }

  /**
   * @return the name of the database of the current {@link InternalDataReader}
   */
  public String getDatabaseName() {
    return DATABASE_NODE_SEARCHER.getDatabaseNameFromNodeDatabase(nodeDatabase);
  }

  /**
   * @param tableName
   * @return the number of entities of the table with the given tableName
   */
  public int getEntityCount(String tableName) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    return TABLE_NODE_SEARCHER.getEntityNodeCount(tableNode);
  }

  /**
   * @return the schema timestamp of the database of the current
   *         {@link InternalDataReader}
   */
  public ITime getSchemaTimestamp() {
    final var databasePropertiesNode = DATABASE_NODE_SEARCHER
      .getStoredDatabasePropertiesNodeFromNodeDatabase(nodeDatabase);

    return DATABASE_PROPERTIES_NODE_SEARCHER.getSchemaTimestampFromDatabasePropertiesNode(databasePropertiesNode);
  }

  /**
   * @param tableView
   * @return the entities of the table of the given tableView
   */
  public ExtendedIterable<EntityLoadingDto> loadEntitiesOfTable(final TableInfoDto tableView) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    return TABLE_NODE_SEARCHER
      .getStoredEntityNodes(tableNode)
      .to(rn -> ENTITY_LOADING_DTO_MAPPER.mapEntityNodeToEntityLoadingDto(rn, tableView));
  }

  /**
   * @param tableView
   * @param id
   * @return the entity with the given id of the table of the given tableView
   * @throws RuntimeException if the table of the given tableView does not contain
   *                          an entity with the given id
   */
  public EntityLoadingDto loadEntity(final TableInfoDto tableView, final String id) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNode(tableNode, id);

    return ENTITY_LOADING_DTO_MAPPER.mapEntityNodeToEntityLoadingDto(entityNode, tableView);
  }

  /**
   * @param tableName
   * @param entityId
   * @param multiBackReferenceColumnView
   * @return the ids of the entities the multi back reference of the given
   *         multiBackReferenceColumnView of the entity with the given entityId of
   *         the table with the given tableName references back
   */
  public ExtendedIterable<String> loadMultiBackReferenceBackReferencedEntityIds(
    final String tableName,
    final String entityId,
    final ColumnInfoDto multiBackReferenceColumnView) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNode(tableNode, entityId);
    final var multiBackReferenceColumnOneBasedOrdinalIndex = multiBackReferenceColumnView.oneBasedOrdinalIndex();

    final var multiBackReferenceNode = //
    entityNode.getStoredChildNodeAtOneBasedIndex(multiBackReferenceColumnOneBasedOrdinalIndex);

    final var multiBackReferenceBackReferencedEntityNodes = multiBackReferenceNode.getStoredChildNodes();

    return multiBackReferenceBackReferencedEntityNodes.to(b -> b.getStoredFirstChildNode().getHeader());
  }

  /**
   * @param tableName
   * @param entityId
   * @param multiBackReferenceColumn
   * @return the entries of the multi back reference of the given
   *         multiBackReferenceColumn of the entity with the given entityId of the
   *         table with the given tableName
   */
  public ExtendedIterable<MultiBackReferenceEntryDto> loadMultiBackReferenceEntries(
    final String tableName,
    final String entityId,
    final ColumnInfoDto multiBackReferenceColumn) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNode(tableNode, entityId);
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

  /**
   * @param tableName
   * @param entityId
   * @param multiReferenceColumnView
   * @return the entries of the multi reference of the given
   *         multiReferenceColumnView of the entity with the given entityId of the
   *         table with the given tableName
   */
  public ExtendedIterable<MultiReferenceEntryDto> loadMultiReferenceEntries(
    final String tableName,
    final String entityId,
    final ColumnInfoDto multiReferenceColumnView) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNode(tableNode, entityId);
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

  /**
   * @param tableName
   * @param entityId
   * @param multiValueColumnView
   * @return the entries of the multi value of the given multiValueColumnView of
   *         the entity with the given entityId of the table with the given
   *         tableName
   */
  public ExtendedIterable<Object> loadMultiValueEntries(
    final String tableName,
    final String entityId,
    final ColumnInfoDto multiValueColumnView) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNode(tableNode, entityId);
    final var multiValueColumnOneBasedOrdinalIndex = multiValueColumnView.oneBasedOrdinalIndex();
    final var multiValueNode = entityNode.getStoredChildNodeAtOneBasedIndex(multiValueColumnOneBasedOrdinalIndex);

    return multiValueNode
      .getStoredChildNodes()
      .to(a -> VALUE_MAPPER.mapStringToValue(a.getHeader(), multiValueColumnView.dataType()));
  }

  /**
   * @param tableName
   * @param id
   * @return true if the table with the given tableName contains an entity with
   *         the given id, false otherwise
   */
  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    return TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWithGivenId(tableNode, id);
  }

  /**
   * @param tableName
   * @param columnView
   * @param value
   * @return true if the table with the given tableName contains an entity with
   *         the given value at the column of the given columnView, false
   *         otherwise
   */
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

  /**
   * @param tableName
   * @param columnView
   * @param value
   * @param entitiesToIgnoreIds
   * @return true if the table with the given tableName contains an entity, that
   *         does not have one of the given entitiesToIgnoreIds, with the given
   *         value at the column of the given columnView, false otherwise
   */
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
}
