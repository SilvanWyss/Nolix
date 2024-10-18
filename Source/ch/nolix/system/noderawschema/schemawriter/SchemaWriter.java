package ch.nolix.system.noderawschema.schemawriter;

import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.noderawschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.TableNodeSearcher;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class SchemaWriter implements ISchemaWriter {

  private static final DatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final DatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  private static final TableNodeMapper TABLE_NODE_MAPPER = new TableNodeMapper();

  private static final ColumnNodeMapper columnNodeMapper = new ColumnNodeMapper();

  private static final ParameterizedFieldTypeNodeMapper parameterizedFieldTypeNodeMapper = //
  new ParameterizedFieldTypeNodeMapper();

  private final CloseController closeController = CloseController.forElement(this);

  private int saveCount;

  private final IMutableNode<?> databaseNode;

  private IMutableNode<?> editedDatabaseNode;

  private boolean hasChanges;

  private SchemaWriter(final IMutableNode<?> databaseNode) {

    GlobalValidator.assertThat(databaseNode).thatIsNamed("database Node").isNotNull();

    this.databaseNode = databaseNode;

    reset();
  }

  public static SchemaWriter forDatabaseNode(final IMutableNode<?> databaseNode) {
    return new SchemaWriter(databaseNode);
  }

  @Override
  public void addColumn(final String tableName, final IColumnDto column) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(editedDatabaseNode,
      tableName);

    tableNode.addChildNode(columnNodeMapper.createColumnNodeFrom(column));

    hasChanges = true;
  }

  @Override
  public void addTable(final ITableDto table) {

    editedDatabaseNode.addChildNode(TABLE_NODE_MAPPER.createTableNodeFrom(table));

    hasChanges = true;
  }

  @Override
  public void deleteColumn(final String tableName, final String columnName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(editedDatabaseNode,
      tableName);

    tableNode.removeFirstChildNodeThat(
      (final INode<?> a) -> a.hasHeader(StructureHeaderCatalogue.COLUMN)
      && COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode((IMutableNode<?>) a).getStoredSingleChildNode()
        .hasHeader(columnName));

    hasChanges = true;
  }

  @Override
  public void deleteTable(final String tableName) {

    editedDatabaseNode.removeFirstChildNodeThat(
      (final INode<?> a) -> a.hasHeader(StructureHeaderCatalogue.TABLE)
      && TABLE_NODE_SEARCHER.getStoredNameNodeFromTableNode((IMutableNode<?>) a).getStoredSingleChildNode()
        .hasHeader(tableName));

    hasChanges = true;
  }

  @Override
  public CloseController getStoredCloseController() {
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

    editedDatabaseNode = MutableNode.fromNode(databaseNode);

    hasChanges = false;
  }

  @Override
  public void saveChanges() {
    try {

      setSchemaTimestamp(Time.ofNow());
      databaseNode.setChildNodes(editedDatabaseNode.getStoredChildNodes());

      saveCount++;
    } finally {
      reset();
    }
  }

  @Override
  public void setColumnName(final String tableName, final String columnName, final String newColumnName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(editedDatabaseNode,
      tableName);

    final var columnNode = TABLE_NODE_SEARCHER.getStoredColumnNodeFromTableNodeByColumnName(tableNode, columnName);
    final var headerNode = COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(columnNode);
    headerNode.setHeader(newColumnName);

    hasChanges = true;
  }

  @Override
  public void setColumnParameterizedFieldType(
    final String columnId,
    final IParameterizedFieldTypeDto parameterizedFieldType) {

    final var columnNode = DATABASE_NODE_SEARCHER.getStoredColumnNodeByColumnIdFromDatabaseNode(databaseNode, columnId);

    columnNode.replaceFirstChildNodeWithGivenHeaderByGivenNode(
      StructureHeaderCatalogue.PARAMETERIZED_FIELD_TYPE,
      parameterizedFieldTypeNodeMapper.createParameterizedFieldTypeNodeFrom(parameterizedFieldType));

    hasChanges = true;
  }

  @Override
  public void setTableName(final String tableName, final String newTableName) {

    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromDatabaseNode(editedDatabaseNode,
      tableName);

    final var nameNode = TABLE_NODE_SEARCHER.getStoredNameNodeFromTableNode(tableNode);
    nameNode.getStoredSingleChildNode().setHeader(newTableName);

    hasChanges = true;
  }

  private void setSchemaTimestamp(final ITime schemaTimestamp) {

    final var databasePropertiesNode = DATABASE_NODE_SEARCHER
      .getStoredDatabasePropertiesNodeFromDatabaseNode(editedDatabaseNode);

    final var schemaTimestampNode = DATABASE_PROPERTIES_NODE_SEARCHER
      .getStoredSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);

    schemaTimestampNode
      .getStoredSingleChildNode()
      .setHeader(schemaTimestamp.getSpecification().getSingleChildNodeHeader());

    hasChanges = true;
  }
}
