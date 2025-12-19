package ch.nolix.system.nodemidschema.schemawriter;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.system.nodemidschema.nodemapper.ColumnNodeMapper;
import ch.nolix.system.nodemidschema.nodemapper.TableNodeMapper;
import ch.nolix.system.nodemidschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.system.nodemidschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.system.nodemidschema.nodesearcher.DatabasePropertiesNodeSearcher;
import ch.nolix.system.nodemidschema.nodesearcher.TableNodeSearcher;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodemapper.IColumnNodeMapper;
import ch.nolix.systemapi.nodemidschema.nodemapper.ITableNodeMapper;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IColumnNodeSearcher;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IDatabaseNodeSearcher;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IDatabasePropertiesNodeSearcher;
import ch.nolix.systemapi.nodemidschema.nodesearcher.ITableNodeSearcher;
import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 */
public final class SchemaWriterActionProvider {
  private static final IDatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final IDatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final IColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  private static final ITableNodeMapper TABLE_NODE_MAPPER = new TableNodeMapper();

  private static final IColumnNodeMapper COLUMN_NODE_MAPPER = new ColumnNodeMapper();

  private SchemaWriterActionProvider() {
  }

  public static void addColumn(
    final IMutableNode<?> nodeDatabase,
    final TableIdentification table,
    final ColumnDto column) {
    final var tableName = table.tableName();

    final var tableNode = //
    DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);

    tableNode.addChildNode(COLUMN_NODE_MAPPER.mapColumnDtoToColumnNode(column));
  }

  public static void addTable(final IMutableNode<?> nodeDatabase, final TableDto table) {
    nodeDatabase.addChildNode(TABLE_NODE_MAPPER.mapTableDtoToNode(table));
  }

  public static void deleteColumn(
    final IMutableNode<?> nodeDatabase,
    final TableIdentification table,
    final String columnName) {
    final var tableId = table.tableId();

    final var tableNode = //
    DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromNodeDatabase(nodeDatabase, tableId);

    tableNode.removeFirstChildNodeThat(
      (final INode<?> a) -> a.hasHeader(NodeHeaderCatalog.COLUMN)
      && COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode((IMutableNode<?>) a).getStoredSingleChildNode()
        .hasHeader(columnName));
  }

  public static void deleteTable(final IMutableNode<?> nodeDatabase, final String tableName) {
    nodeDatabase.removeFirstChildNodeThat(
      (final INode<?> a) -> a.hasHeader(NodeHeaderCatalog.TABLE)
      && TABLE_NODE_SEARCHER.getStoredNameNodeFromTableNode((IMutableNode<?>) a).getStoredSingleChildNode()
        .hasHeader(tableName));
  }

  public static void renameColumn(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String columnName,
    final String newColumnName) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var columnNode = TABLE_NODE_SEARCHER.getStoredColumnNodeFromTableNodeByColumnName(tableNode, columnName);
    final var headerNode = COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(columnNode);

    headerNode.setHeader(newColumnName);
  }

  public static void renameTable(
    final IMutableNode<?> nodeDatabase,
    final String tableName,
    final String newTableName) {
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableNameFromNodeDatabase(nodeDatabase, tableName);
    final var nameNode = TABLE_NODE_SEARCHER.getStoredNameNodeFromTableNode(tableNode);

    nameNode.getStoredSingleChildNode().setHeader(newTableName);
  }

  public static void setColumnModel(
    final IMutableNode<?> nodeDatabase,
    final TableIdentification table,
    final ColumnIdentification column,
    final FieldType fieldType,
    final DataType dataType,
    final IContainer<String> referenceableTableIds,
    final IContainer<String> backReferenceableColumnIds) {
    final var tableId = table.tableId();
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromNodeDatabase(nodeDatabase, tableId);
    final var columnId = column.columnId();
    final var columnNode = TABLE_NODE_SEARCHER.getStoredColumnNodeFromTableNodeByColumnId(tableNode, columnId);

    final var fieldTypeNode = COLUMN_NODE_SEARCHER.getStoredFieldTypeNodeFromColumnNode(columnNode);
    fieldTypeNode.getStoredSingleChildNode().setHeader(fieldType.name());

    final var dataTypeNode = COLUMN_NODE_SEARCHER.getStoredDataTypeNodeFromColumnNode(columnNode);
    dataTypeNode.getStoredSingleChildNode().setHeader(dataType.name());

    final var referenceablteTableIdsNodesView = referenceableTableIds.getViewOf(Node::withHeader);

    final var referenceablteTableIdsNode = //
    COLUMN_NODE_SEARCHER.getStoredReferenceableTableIdsNodeFromColumnNode(columnNode);

    referenceablteTableIdsNode.setChildNodes(referenceablteTableIdsNodesView);

    final var backReferenceableColumnIdNodes = backReferenceableColumnIds.to(Node::withHeader);

    final var backReferenceableColumnIdsNode = //
    COLUMN_NODE_SEARCHER.getStoredBackReferenceableColumnIdsNodeFromColumnNode(columnNode);

    backReferenceableColumnIdsNode.setChildNodes(backReferenceableColumnIdNodes);
  }

  public static void setSchemaTimestamp(final IMutableNode<?> nodeDatabase, final ITime schemaTimestamp) {
    final var databasePropertiesNode = //
    DATABASE_NODE_SEARCHER.getStoredDatabasePropertiesNodeFromNodeDatabase(nodeDatabase);

    final var schemaTimestampNode = //
    DATABASE_PROPERTIES_NODE_SEARCHER.getStoredSchemaTimestampNodeFromDatabasePropertiesNode(databasePropertiesNode);

    schemaTimestampNode
      .getStoredSingleChildNode()
      .setHeader(schemaTimestamp.getSpecification().getSingleChildNodeHeader());
  }
}
