package ch.nolix.system.noderawdata.datareader;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.modelmapper.EntityLoadingDtoMapper;
import ch.nolix.system.noderawdata.nodeexaminer.TableNodeExaminer;
import ch.nolix.system.noderawdata.nodesearcher.TableNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.system.rawdata.valuemapper.ValueMapper;
import ch.nolix.systemapi.noderawdataapi.nodeexaminerapi.ITableNodeExaminer;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.ITableNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabaseNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableViewDto;
import ch.nolix.systemapi.rawdataapi.valuemapperapi.IValueMapper;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class InternalDataReader {

  private static final IDatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final IDatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final ITableNodeExaminer TABLE_NODE_EXAMINER = new TableNodeExaminer();

  private static final EntityLoadingDtoMapper ENTITY_LOADING_DTO_MAPPER = new EntityLoadingDtoMapper();

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

  public IContainer<EntityLoadingDto> loadEntitiesOfTable(final TableViewDto tableView) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    return TABLE_NODE_SEARCHER
      .getStoredEntityNodesFromTableNode(tableNode)
      .to(rn -> ENTITY_LOADING_DTO_MAPPER.mapEntiyNodeToEntityLoadingDto(rn, tableView));
  }

  public IContainer<String> loadMultiBackReferenceEntries(
    final TableViewDto tableView,
    final String entityId,
    final ColumnViewDto multiBackReferenceColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiBackReferenceColumnIndex = multiBackReferenceColumnInfo.oneBasedOrdinalIndex();

    final var multiBackReferenceNode = entityNode.getStoredChildNodeAt1BasedIndex(multiBackReferenceColumnIndex);

    return multiBackReferenceNode.getChildNodesHeaders();
  }

  public IContainer<String> loadMultiReferenceEntries(
    final TableViewDto tableView,
    final String entityId,
    final ColumnViewDto multiReferenceColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceColumnIndex = multiReferenceColumnInfo.oneBasedOrdinalIndex();

    final var multiReferenceNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    return multiReferenceNode.getChildNodesHeaders();
  }

  public IContainer<Object> loadMultiValueEntries(
    final TableViewDto tableView,
    final String entityId,
    final ColumnViewDto multiValueColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiValueColumnIndex = multiValueColumnInfo.oneBasedOrdinalIndex();

    final var multiValueNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnIndex);

    return multiValueNode
      .getStoredChildNodes()
      .to(a -> VALUE_MAPPER.mapStringToValue(a.getHeader(), multiValueColumnInfo.dataType()));
  }

  public EntityLoadingDto loadEntity(final TableViewDto tableView, final String id) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase,
      tableView.name());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, id);

    return ENTITY_LOADING_DTO_MAPPER.mapEntiyNodeToEntityLoadingDto(entityNode, tableView);
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final TableViewDto tableView,
    final ColumnViewDto columnInfo,
    final String value) {

    final var tableNode = //
    DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableView.name());

    final var columnIndex = columnInfo.oneBasedOrdinalIndex();

    return //
    TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenValue(
      tableNode,
      columnIndex,
      value);
  }

  public boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final TableViewDto tableView,
    final ColumnViewDto columnInfo,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {

    final var tableNode = //
    DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableView.name());

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
