package ch.nolix.system.nodemiddata.nodesearcher;

import java.util.Optional;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.nodemiddata.nodesearcher.ITableNodeSearcher;
import ch.nolix.systemapi.nodemidschema.databasestructure.FieldIndexCatalog;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;

/**
 * @author Silvan Wyss
 */
public final class TableNodeSearcher implements ITableNodeSearcher {
  @Override
  public int getEntityNodeCountOfTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getChildNodeCount(c -> c.hasHeader(NodeHeaderCatalog.ENTITY));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<? extends IMutableNode<?>> getOptionalStoredEntityNodeFromTableNode(
    final IMutableNode<?> tableNode,
    final String id) {
    return tableNode.getOptionalStoredFirstChildNodeThat(
      a -> a.hasHeader(NodeHeaderCatalog.ENTITY)
      && a.getStoredChildNodeAtOneBasedIndex(FieldIndexCatalog.ID_INDEX).hasHeader(id));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends IMutableNode<?>> getStoredColumnNodesFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredChildNodesWithHeader(NodeHeaderCatalog.COLUMN);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredEntityNodeFromTableNode(final IMutableNode<?> tableNode, final String id) {
    return tableNode.getStoredFirstChildNodeThat(
      a -> a.hasHeader(NodeHeaderCatalog.ENTITY)
      && a.getStoredChildNodeAtOneBasedIndex(FieldIndexCatalog.ID_INDEX).hasHeader(id));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends IMutableNode<?>> getStoredEntityNodesFromTableNode(final IMutableNode<?> tableNode) {
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
