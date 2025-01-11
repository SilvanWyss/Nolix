package ch.nolix.system.noderawdata.nodesearcher;

import java.util.Optional;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.tabledefinition.FieldIndexCatalog;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.ITableNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;

public final class TableNodeSearcher implements ITableNodeSearcher {

  @Override
  public Optional<? extends IMutableNode<?>> getOptionalStoredEntityNodeFromTableNode(
    final IMutableNode<?> tableNode,
    final String id) {
    return tableNode.getOptionalStoredFirstChildNodeThat(
      a -> a.hasHeader(NodeHeaderCatalog.ENTITY)
      && a.getStoredChildNodeAt1BasedIndex(FieldIndexCatalog.ID_INDEX).hasHeader(id));
  }

  @Override
  public IMutableNode<?> getStoredEntityNodeFromTableNode(final IMutableNode<?> tableNode, final String id) {
    return tableNode.getStoredFirstChildNodeThat(
      a -> a.hasHeader(NodeHeaderCatalog.ENTITY)
      && a.getStoredChildNodeAt1BasedIndex(FieldIndexCatalog.ID_INDEX).hasHeader(id));
  }

  @Override
  public IContainer<? extends IMutableNode<?>> getStoredEntityNodesFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredChildNodesWithHeader(NodeHeaderCatalog.ENTITY);
  }
}
