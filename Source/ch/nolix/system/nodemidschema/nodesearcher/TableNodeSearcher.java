/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.nodesearcher;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.midschema.databasestructure.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodesearcher.ITableNodeSearcher;

/**
 * @author Silvan Wyss
 */
public final class TableNodeSearcher implements ITableNodeSearcher {
  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  @Override
  public int getOneBasedIndexOfColumnInTableNodeByColumnName(final IMutableNode<?> tableNode, final String columnName) {
    var oneBasedColumnIndex = FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS + 1;

    for (final var c : getStoredColumnNodesFromTableNode(tableNode)) {
      if (COLUMN_NODE_SEARCHER.getColumnNameFromColumnNode(c).equals(columnName)) {
        return oneBasedColumnIndex;
      }

      oneBasedColumnIndex++;
    }

    throw InvalidArgumentException.forArgumentAndArgumentName(columnName, "column name");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredColumnNodeFromTableNodeByColumnId(
    final IMutableNode<?> tableNode,
    final String columnId) {
    final var columnNodes = getStoredColumnNodesFromTableNode(tableNode);

    return //
    columnNodes.getStoredFirst(
      c -> COLUMN_NODE_SEARCHER.getStoredIdNodeFromColumnNode(c).getStoredSingleChildNode().hasHeader(columnId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredColumnNodeFromTableNodeByColumnName(
    final IMutableNode<?> tableNode,
    final String columnName) {
    final var columnNodes = getStoredColumnNodesFromTableNode(tableNode);

    return //
    columnNodes.getStoredFirst(
      c -> COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(c).getStoredSingleChildNode().hasHeader(columnName));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends IMutableNode<?>> getStoredColumnNodesFromTableNode(
    final IMutableNode<?> tableNode) {
    return tableNode.getStoredChildNodesWithHeader(NodeHeaderCatalog.COLUMN);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends IMutableNode<?>> getStoredEntityNodesFromTableNode(
    final IMutableNode<?> tableNode) {
    return tableNode.getStoredChildNodesWithHeader(NodeHeaderCatalog.ENTITY);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredIdNodeFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.ID);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredNameNodeFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.NAME);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTableIdFromTableNode(final IMutableNode<?> tableNode) {
    final var idNode = getStoredIdNodeFromTableNode(tableNode);

    return idNode.getSingleChildNodeHeader();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTableNameFromTableNode(final IMutableNode<?> tableNode) {
    final var nameNode = getStoredNameNodeFromTableNode(tableNode);

    return nameNode.getSingleChildNodeHeader();
  }
}
