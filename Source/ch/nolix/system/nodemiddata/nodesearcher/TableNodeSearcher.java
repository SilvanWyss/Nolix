/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.nodesearcher;

import java.util.Optional;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.systemapi.nodemiddata.nodesearcher.ITableNodeSearcher;
import ch.nolix.systemapi.nodemidschema.databasestructure.FieldIndexCatalog;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;

/**
 * @author Silvan Wyss
 */
public final class TableNodeSearcher implements ITableNodeSearcher {
  /**
   * {@inheritDoc}
   */
  @Override
  public int getEntityNodeCount(final IMutableNode<?> tableNode) {
    return tableNode.getChildNodeCount(c -> c.hasHeader(NodeHeaderCatalog.ENTITY));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<? extends IMutableNode<?>> getOptionalStoredEntity(
    final IMutableNode<?> tableNode,
    final String entityId) {
    return //
    tableNode.getOptionalStoredFirstChildNode(
      a -> a.hasHeader(NodeHeaderCatalog.ENTITY)
      && a.getStoredChildNodeAtOneBasedIndex(FieldIndexCatalog.ID_INDEX).hasHeader(entityId));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends IMutableNode<?>> getStoredColumnNodes(final IMutableNode<?> tableNode) {
    return tableNode.getStoredChildNodesWithHeader(NodeHeaderCatalog.COLUMN);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredEntityNode(final IMutableNode<?> tableNode, final String id) {
    return //
    tableNode.getStoredFirstChildNode(
      a -> a.hasHeader(NodeHeaderCatalog.ENTITY)
      && a.getStoredChildNodeAtOneBasedIndex(FieldIndexCatalog.ID_INDEX).hasHeader(id));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends IMutableNode<?>> getStoredEntityNodes(final IMutableNode<?> tableNode) {
    return tableNode.getStoredChildNodesWithHeader(NodeHeaderCatalog.ENTITY);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredIdNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.ID);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredNameNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.NAME);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTableId(final IMutableNode<?> tableNode) {
    final var idNode = getStoredIdNode(tableNode);

    return idNode.getSingleChildNodeHeader();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTableName(final IMutableNode<?> tableNode) {
    final var nameNode = getStoredNameNode(tableNode);

    return nameNode.getSingleChildNodeHeader();
  }
}
