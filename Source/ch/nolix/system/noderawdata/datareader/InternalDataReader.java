package ch.nolix.system.noderawdata.datareader;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.dtomapper.LoadedEntityDtoMapper;
import ch.nolix.system.noderawdata.nodesearcher.TableNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.NodeDatabaseSearcher;
import ch.nolix.system.sqlrawdata.datareader.ValueMapper;
import ch.nolix.systemapi.rawdataapi.datadto.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class InternalDataReader {

  private static final NodeDatabaseSearcher DATABASE_NODE_SEARCHER = new NodeDatabaseSearcher();

  private static final DatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final LoadedEntityDtoMapper LOADED_ENTITY_DTO_MAPPER = new LoadedEntityDtoMapper();

  private static final ValueMapper VALUE_MAPPER = new ValueMapper();

  private final IMutableNode<?> nodeDatabase;

  public InternalDataReader(final IMutableNode<?> nodeDatabase) {

    GlobalValidator.assertThat(nodeDatabase).thatIsNamed("database node").isNotNull();

    this.nodeDatabase = nodeDatabase;
  }

  public ITime getSchemaTimestamp() {

    final var databasePropertiesNode = DATABASE_NODE_SEARCHER
      .getStoredDatabasePropertiesNodeFromNodeDatabase(nodeDatabase);

    return DATABASE_PROPERTIES_NODE_SEARCHER.getSchemaTimestampFromDatabasePropertiesNode(databasePropertiesNode);
  }

  public IContainer<EntityLoadingDto> loadEntitiesOfTable(final ITableInfo tableInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableInfo.getTableName());

    return TABLE_NODE_SEARCHER
      .getStoredEntityNodesFromTableNode(tableNode)
      .to(rn -> LOADED_ENTITY_DTO_MAPPER.createLoadedEntityDtoFromEntityNode(rn, tableInfo));
  }

  public IContainer<String> loadMultiBackReferenceEntries(
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiBackReferenceColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiBackReferenceColumnIndex = multiBackReferenceColumnInfo.getColumnIndexOnEntityNode();

    final var multiBackReferenceNode = entityNode.getStoredChildNodeAt1BasedIndex(multiBackReferenceColumnIndex);

    return multiBackReferenceNode.getChildNodesHeaders();
  }

  public IContainer<String> loadMultiReferenceEntries(
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiReferenceColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceColumnIndex = multiReferenceColumnInfo.getColumnIndexOnEntityNode();

    final var multiReferenceNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    return multiReferenceNode.getChildNodesHeaders();
  }

  public IContainer<Object> loadMultiValueEntries(
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiValueColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiValueColumnIndex = multiValueColumnInfo.getColumnIndexOnEntityNode();

    final var multiValueNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnIndex);

    return multiValueNode
      .getStoredChildNodes()
      .to(a -> VALUE_MAPPER.createValueFromString(a.getHeader(), multiValueColumnInfo));
  }

  public EntityLoadingDto loadEntity(final ITableInfo tableInfo, final String id) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, id);

    return LOADED_ENTITY_DTO_MAPPER.createLoadedEntityDtoFromEntityNode(entityNode, tableInfo);
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final ITableInfo tableInfo,
    final IColumnInfo columnInfo,
    final String value) {

    final var tableNode = //
    DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableInfo.getTableName());

    final var columnIndex = columnInfo.getColumnIndexOnEntityNode();

    return //
    TABLE_NODE_SEARCHER.tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenValue(
      tableNode,
      columnIndex,
      value);
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final ITableInfo tableInfo,
    final IColumnInfo columnInfo,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var tableNode = //
    DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableInfo.getTableName());

    final var local1BasedColumnIndex = columnInfo.getColumnIndexOnEntityNode();

    return //
    TABLE_NODE_SEARCHER.containsEntityNodeWithFieldAtGiven1BasedIndexWithGivenValueIgnoringGivenEntities(
      tableNode,
      local1BasedColumnIndex,
      value,
      entitiesToIgnoreIds);
  }

  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    return TABLE_NODE_SEARCHER.tableNodeContainsEntityNodeWithGivenId(tableNode, id);
  }
}
