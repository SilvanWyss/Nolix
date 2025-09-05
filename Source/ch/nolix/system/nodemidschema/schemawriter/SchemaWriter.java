package ch.nolix.system.nodemidschema.schemawriter;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.system.nodemidschema.nodemapper.ColumnNodeMapper;
import ch.nolix.system.nodemidschema.nodemapper.ContentModelNodeMapper;
import ch.nolix.system.nodemidschema.nodemapper.TableNodeMapper;
import ch.nolix.system.nodemidschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.system.nodemidschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.system.nodemidschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.system.nodemidschema.nodesearcher.TableNodeSearcher;
import ch.nolix.system.time.moment.IncrementalCurrentTimeCreator;
import ch.nolix.systemapi.midschema.adapter.ISchemaWriter;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.IContentModelDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodemapper.IColumnNodeMapper;
import ch.nolix.systemapi.nodemidschema.nodemapper.IContentModelNodeMapper;
import ch.nolix.systemapi.nodemidschema.nodemapper.ITableNodeMapper;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IColumnNodeSearcher;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IDatabaseNodeSearcher;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.nodemidschema.nodesearcher.ITableNodeSearcher;
import ch.nolix.systemapi.time.moment.IIncrementalCurrentTimeCreator;
import ch.nolix.systemapi.time.moment.ITime;

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
    Validator.assertThat(nodeDatabase).thatIsNamed("database Node").isNotNull();

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

    tableNode.addChildNode(COLUMN_NODE_MAPPER.mapColumnDtoToColumnNode(column));

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
      (final INode<?> a) -> a.hasHeader(NodeHeaderCatalog.COLUMN)
      && COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode((IMutableNode<?>) a).getStoredSingleChildNode()
        .hasHeader(columnName));

    hasChanges = true;
  }

  @Override
  public void deleteTable(final String tableName) {
    editedNodeDatabase.removeFirstChildNodeThat(
      (final INode<?> a) -> a.hasHeader(NodeHeaderCatalog.TABLE)
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
  public void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(editedNodeDatabase,
      tableName);

    final var columnNode = TABLE_NODE_SEARCHER.getStoredColumnNodeFromTableNodeByColumnName(tableNode, columnName);
    final var headerNode = COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(columnNode);
    headerNode.setHeader(newColumnName);

    hasChanges = true;
  }

  @Override
  public void setContentModel(final String tableName, final String columnName, final IContentModelDto contentModel) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var columnNode = TABLE_NODE_SEARCHER.getStoredColumnNodeFromTableNodeByColumnName(tableNode, columnName);

    columnNode.replaceFirstChildNodeWithGivenHeaderByGivenNode(
      NodeHeaderCatalog.CONTENT_MODEL,
      CONTENT_MODEL_NODE_MAPPER.mapContentModelDtoToNode(contentModel));

    hasChanges = true;
  }

  @Override
  public void renameTable(final String tableName, final String newTableName) {
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
