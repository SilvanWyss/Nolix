/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.schemawriter;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.datamodel.fieldproperty.DataType;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.document.node.Node;
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
import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 */
public final class SchemaWriterActionProvider {
  private static final DatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final DatabasePropertiesNodeSearcher DATABASE_PROPERTIES_NODE_SEARCHER = //
  new DatabasePropertiesNodeSearcher();

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  private static final TableNodeMapper TABLE_NODE_MAPPER = new TableNodeMapper();

  private static final ColumnNodeMapper COLUMN_NODE_MAPPER = new ColumnNodeMapper();

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
      (final Node<?> a) -> a.hasHeader(NodeHeaderCatalog.COLUMN)
      && COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode((IMutableNode<?>) a).getStoredSingleChildNode()
        .hasHeader(columnName));
  }

  public static void deleteTable(final IMutableNode<?> nodeDatabase, final String tableName) {
    nodeDatabase.removeFirstChildNodeThat(
      (final Node<?> a) -> a.hasHeader(NodeHeaderCatalog.TABLE)
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
    final ExtendedIterable<String> referenceableTableIds,
    final ExtendedIterable<String> backReferenceableColumnIds) {
    final var tableId = table.tableId();
    final var tableNode = DATABASE_NODE_SEARCHER.getStoredTableNodeByTableIdFromNodeDatabase(nodeDatabase, tableId);
    final var columnId = column.columnId();
    final var columnNode = TABLE_NODE_SEARCHER.getStoredColumnNodeFromTableNodeByColumnId(tableNode, columnId);

    final var fieldTypeNode = COLUMN_NODE_SEARCHER.getStoredFieldTypeNodeFromColumnNode(columnNode);
    fieldTypeNode.getStoredSingleChildNode().setHeader(fieldType.name());

    final var dataTypeNode = COLUMN_NODE_SEARCHER.getStoredDataTypeNodeFromColumnNode(columnNode);
    dataTypeNode.getStoredSingleChildNode().setHeader(dataType.name());

    final var referenceablteTableIdsNodesView = referenceableTableIds.getViewOf(ImmutableNode::withHeader);

    final var referenceablteTableIdsNode = //
    COLUMN_NODE_SEARCHER.getStoredReferenceableTableIdsNodeFromColumnNode(columnNode);

    referenceablteTableIdsNode.setChildNodes(referenceablteTableIdsNodesView);

    final var backReferenceableColumnIdNodes = backReferenceableColumnIds.to(ImmutableNode::withHeader);

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
