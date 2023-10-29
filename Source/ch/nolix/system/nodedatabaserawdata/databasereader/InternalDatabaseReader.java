//package declaration
package ch.nolix.system.nodedatabaserawdata.databasereader;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.structure.TableNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabaseNodeSearcher;
import ch.nolix.system.nodedatabaserawschema.structure.DatabasePropertiesNodeSearcher;
import ch.nolix.system.sqldatabaserawdata.databasereader.ValueMapper;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDto;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
public final class InternalDatabaseReader {

  //constant
  private static final DatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  //constant
  private static final DatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  //constant
  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  //constant
  private static final LoadedEntityDtoMapper LOADED_ENTITY_DTO_MAPPER = new LoadedEntityDtoMapper();

  //constant
  private static final ValueMapper VALUE_MAPPER = new ValueMapper();

  //attribute
  private final IMutableNode<?> databaseNode;

  //constructor
  public InternalDatabaseReader(final IMutableNode<?> databaseNode) {

    GlobalValidator.assertThat(databaseNode).thatIsNamed("database node").isNotNull();

    this.databaseNode = databaseNode;
  }

  //method
  public Time getSchemaTimestamp() {

    final var databasePropertiesNode = DATABASE_NODE_SEARCHER
      .getStoredDatabasePropertiesNodeFromDatabaseNode(databaseNode);

    return DATABASE_PROPERTIES_NODE_SEARCHER.getSchemaTimestampFromDatabasePropertiesNode(databasePropertiesNode);
  }

  //method
  public IContainer<ILoadedEntityDto> loadEntitiesOfTable(final ITableInfo tableInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode,
      tableInfo.getTableName());

    return TABLE_NODE_SEARCHER
      .getStoredEntityNodesFromTableNode(tableNode)
      .to(rn -> LOADED_ENTITY_DTO_MAPPER.createLoadedEntityDtoFromEntityNode(rn, tableInfo));
  }

  //method
  public IContainer<String> loadMultiReferenceEntries(
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiReferenceColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiReferenceColumnIndex = multiReferenceColumnInfo.getColumnIndexOnEntityNode();

    final var multiValueNode = entityNode.getStoredChildNodeAt1BasedIndex(multiReferenceColumnIndex);

    return multiValueNode.getChildNodesHeaders();
  }

  //method
  public IContainer<Object> loadMultiValueEntries(
    final ITableInfo tableInfo,
    final String entityId,
    final IColumnInfo multiValueColumnInfo) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, entityId);

    final var multiValueColumnIndex = multiValueColumnInfo.getColumnIndexOnEntityNode();

    final var multiValueNode = entityNode.getStoredChildNodeAt1BasedIndex(multiValueColumnIndex);

    return multiValueNode
      .getStoredChildNodes()
      .to(a -> VALUE_MAPPER.createValueFromString(a.getHeader(), multiValueColumnInfo));
  }

  //method
  public ILoadedEntityDto loadEntity(final ITableInfo tableInfo, final String id) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode,
      tableInfo.getTableName());

    final var entityNode = TABLE_NODE_SEARCHER.getStoredEntityNodeFromTableNode(tableNode, id);

    return LOADED_ENTITY_DTO_MAPPER.createLoadedEntityDtoFromEntityNode(entityNode, tableInfo);
  }

  //method
  public boolean tableContainsEntityWithGivenValueAtGivenColumn(
    final ITableInfo tableInfo,
    final IColumnInfo columnInfo,
    final String value) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode,
      tableInfo.getTableName());

    final var columnIndex = columnInfo.getColumnIndexOnEntityNode();

    return TABLE_NODE_SEARCHER.tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenValue(
      tableNode,
      columnIndex,
      value);
  }

  //method
  public boolean tableContainsEntityWithGivenId(final String tableName, final String id) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(databaseNode, tableName);

    return TABLE_NODE_SEARCHER.tableNodeContainsEntityNodeWithGivenId(tableNode, id);
  }
}
