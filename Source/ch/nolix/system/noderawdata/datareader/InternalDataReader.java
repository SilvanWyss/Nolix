package ch.nolix.system.noderawdata.datareader;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.dtomapper.LoadedEntityDtoMapper;
import ch.nolix.system.noderawdata.nodeexaminer.TableNodeExaminer;
import ch.nolix.system.noderawdata.nodesearcher.TableNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.system.sqlrawdata.datamapper.ValueMapper;
import ch.nolix.systemapi.noderawdataapi.nodeexaminerapi.ITableNodeExaminer;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.ITableNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabaseNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.rawdataapi.model.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableView;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.ColumnViewDto;
import ch.nolix.systemapi.sqlrawdataapi.datamapperapi.IValueMapper;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class InternalDataReader {

  private static final IDatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final IDatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final ITableNodeExaminer TABLE_NODE_EXAMINER = new TableNodeExaminer();

  private static final LoadedEntityDtoMapper LOADED_ENTITY_DTO_MAPPER = new LoadedEntityDtoMapper();

  private static final IValueMapper VALUE_MAPPER = new ValueMapper();

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

  public IContainer<EntityLoadingDto> loadEntitiesOfTable(final ITableView tableView) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.getTableName());

    return TABLE_NODE_SEARCHER
      .getStoredEntityNodesFromTableNode(tableNode)
      .to(rn -> LOADED_ENTITY_DTO_MAPPER.createLoadedEntityDtoFromEntityNode(rn, tableView));
  }

  public IContainer<String> loadMultiBackReferenceEntries(
    final ITableView tableView,
    final String entityId,
    final ColumnViewDto multiBackReferenceColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiBackReferenceColumnIndex = multiBackReferenceColumnInfo.oneBasedOrdinalIndex();

    final var multiBackReferenceNode = entityNode.getStoredChildNodeAt1BasedIndex(multiBackReferenceColumnIndex);

    return multiBackReferenceNode.getChildNodesHeaders();
  }

  public IContainer<String> loadMultiReferenceEntries(
    final ITableView tableView,
    final String entityId,
    final ColumnViewDto multiReferenceColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceColumnIndex = multiReferenceColumnInfo.oneBasedOrdinalIndex();

    final var multiReferenceNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    return multiReferenceNode.getChildNodesHeaders();
  }

  public IContainer<Object> loadMultiValueEntries(
    final ITableView tableView,
    final String entityId,
    final ColumnViewDto multiValueColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiValueColumnIndex = multiValueColumnInfo.oneBasedOrdinalIndex();

    final var multiValueNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnIndex);

    return multiValueNode
      .getStoredChildNodes()
      .to(a -> VALUE_MAPPER.mapValueToString(a.getHeader(), multiValueColumnInfo.dataType()));
  }

  public EntityLoadingDto loadEntity(final ITableView tableView, final String id) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, id);

    return LOADED_ENTITY_DTO_MAPPER.createLoadedEntityDtoFromEntityNode(entityNode, tableView);
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final ITableView tableView,
    final ColumnViewDto columnInfo,
    final String value) {

    final var tableNode = //
    DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableView.getTableName());

    final var columnIndex = columnInfo.oneBasedOrdinalIndex();

    return //
    TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenValue(
      tableNode,
      columnIndex,
      value);
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final ITableView tableView,
    final ColumnViewDto columnInfo,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var tableNode = //
    DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableView.getTableName());

    final var local1BasedColumnIndex = columnInfo.oneBasedOrdinalIndex();

    return //
    TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWithFieldAtGiven1BasedIndexWithGivenValueIgnoringGivenEntities(
      tableNode,
      local1BasedColumnIndex,
      value,
      entitiesToIgnoreIds);
  }

  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    return TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWithGivenId(tableNode, id);
  }
}
