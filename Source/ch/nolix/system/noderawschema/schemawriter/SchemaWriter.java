package ch.nolix.system.noderawschema.schemawriter;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.system.noderawschema.nodemapper.ColumnNodeMapper;
import ch.nolix.system.noderawschema.nodemapper.ContentModelNodeMapper;
import ch.nolix.system.noderawschema.nodemapper.TableNodeMapper;
import ch.nolix.system.noderawschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.TableNodeSearcher;
import ch.nolix.system.time.moment.IncrementalCurrentTimeCreator;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.IColumnNodeMapper;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.IContentModelNodeMapper;
import ch.nolix.systemapi.noderawschemaapi.nodemapperapi.ITableNodeMapper;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IColumnNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabaseNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.ITableNodeSearcher;
import ch.nolix.systemapi.rawschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.dto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.TableDto;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.timeapi.momentapi.IIncrementalCurrentTimeCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class SchemaWriter implements ISchemaWriter {

  private static final IDatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final IDatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final IColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  private static final ITableNodeMapper TABLE_NODE_MAPPER = new TableNodeMapper();

  private static final IColumnNodeMapper COLUMN_NODE_MAPPER = new ColumnNodeMapper();

  private static final IContentModelNodeMapper CONTENT_MODEL_NODE_MAPPER = new ContentModelNodeMapper();

  private static final IIncrementalCurrentTimeCreator INCREMENTAL_CURRENT_TIME_CREATOR = //
  new IncrementalCurrentTimeCreator();

  private final ICloseController closeController = CloseController.forElement(this);

  private int saveCount;

  private final IMutableNode<?> nodeDatabase;

  private IMutableNode<?> editedNodeDatabase;

  private boolean hasChanges;

  private SchemaWriter(final IMutableNode<?> nodeDatabase) {

    GlobalValidator.assertThat(nodeDatabase).thatIsNamed("database Node").isNotNull();

    this.nodeDatabase = nodeDatabase;

    reset();
  }

  public static SchemaWriter forNodeDatabase(final IMutableNode<?> nodeDatabase) {
    return new SchemaWriter(nodeDatabase);
  }

  @Override
  public void addColumn(final String tableName, final ColumnDto column) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(editedNodeDatabase,
      tableName);

    tableNode.addChildNode(COLUMN_NODE_MAPPER.mapColumnDtoToNode(column));

    hasChanges = true;
  }

  @Override
  public void addTable(final TableDto table) {

    editedNodeDatabase.addChildNode(TABLE_NODE_MAPPER.mapTableDtoToNode(table));

    hasChanges = true;
  }

  @Override
  public void deleteColumn(final String tableName, final String columnName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(editedNodeDatabase,
      tableName);

    tableNode.removeFirstChildNodeThat(
      (final INode<?> a) -> a.hasHeader(NodeHeaderCatalogue.COLUMN)
      && COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode((IMutableNode<?>) a).getStoredSingleChildNode()
        .hasHeader(columnName));

    hasChanges = true;
  }

  @Override
  public void deleteTable(final String tableName) {

    editedNodeDatabase.removeFirstChildNodeThat(
      (final INode<?> a) -> a.hasHeader(NodeHeaderCatalogue.TABLE)
      && TABLE_NODE_SEARCHER.getStoredNameNodeFromTableNode((IMutableNode<?>) a).getStoredSingleChildNode()
        .hasHeader(tableName));

    hasChanges = true;
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public int getSaveCount() {
    return saveCount;
  }

  @Override
  public boolean hasChanges() {
    return hasChanges;
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  @Override
  public void reset() {

    editedNodeDatabase = MutableNode.fromNode(nodeDatabase);

    hasChanges = false;
  }

  @Override
  public void saveChanges() {
    try {

      setSchemaTimestamp(INCREMENTAL_CURRENT_TIME_CREATOR.getCurrentTime());
      nodeDatabase.setChildNodes(editedNodeDatabase.getStoredChildNodes());

      saveCount++;
    } finally {
      reset();
    }
  }

  @Override
  public void setColumnName(final String tableName, final String columnName, final String newColumnName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(editedNodeDatabase,
      tableName);

    final var columnNode = TABLE_NODE_SEARCHER.getStoredColumnNodeFromTableNodeByColumnName(tableNode, columnName);
    final var headerNode = COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(columnNode);
    headerNode.setHeader(newColumnName);

    hasChanges = true;
  }

  @Override
  public void setColumnContentModel(
    final String columnId,
    final IContentModelDto contentModel) {

    final var columnNode = DATABASE_NODE_SEARCHER.getStoredColumnNodeByColumnIdFromNodeDatabase(nodeDatabase, columnId);

    columnNode.replaceFirstChildNodeWithGivenHeaderByGivenNode(
      NodeHeaderCatalogue.CONTENT_MODEL,
      CONTENT_MODEL_NODE_MAPPER.mapContentModelDtoToNode(contentModel));

    hasChanges = true;
  }

  @Override
  public void setTableName(final String tableName, final String newTableName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(editedNodeDatabase,
      tableName);

    final var nameNode = TABLE_NODE_SEARCHER.getStoredNameNodeFromTableNode(tableNode);
    nameNode.getStoredSingleChildNode().setHeader(newTableName);

    hasChanges = true;
  }

  private void setSchemaTimestamp(final ITime schemaTimestamp) {

    final var databasePropertiesNode = DATABASE_NODE_SEARCHER
      .getStoredDatabasePropertiesNodeFromNodeDatabase(editedNodeDatabase);

    final var schemaTimestampNode = DATABASE_PROPERTIES_NODE_SEARCHER
      .getStoredSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);

    schemaTimestampNode
      .getStoredSingleChildNode()
      .setHeader(schemaTimestamp.getSpecification().getSingleChildNodeHeader());

    hasChanges = true;
  }
}
