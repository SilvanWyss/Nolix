package ch.nolix.system.noderawdata.nodesearcher;

import java.util.Optional;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.tabledefinition.FieldIndexCatalogue;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.ITableNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;

public final class TableNodeSearcher implements ITableNodeSearcher {

  @Override
  public boolean containsEntityNodeWithFieldAtGiven1BasedIndexWithGivenValueIgnoringGivenEntities(
    final IMutableNode<?> tableNode,
    final int param1BasedIndex,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    tableNode.containsChildNodeThat(
      a -> a.hasHeader(NodeHeaderCatalogue.ENTITY)
      && a.getStoredChildNodeAt1BasedIndex(param1BasedIndex).hasHeader(value)
      && !entitiesToIgnoreIds
        .containsEqualing(a.getStoredChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).getHeader()));
  }

  @Override
  public Optional<? extends IMutableNode<?>> getOptionalStoredEntityNodeFromTableNode(
    final IMutableNode<?> tableNode,
    final String id) {
    return tableNode.getOptionalStoredFirstChildNodeThat(
      a -> a.hasHeader(NodeHeaderCatalogue.ENTITY)
      && a.getStoredChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).hasHeader(id));
  }

  @Override
  public IMutableNode<?> getStoredEntityNodeFromTableNode(final IMutableNode<?> tableNode, final String id) {
    return tableNode.getStoredFirstChildNodeThat(
      a -> a.hasHeader(NodeHeaderCatalogue.ENTITY)
      && a.getStoredChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).hasHeader(id));
  }

  @Override
  public IContainer<? extends IMutableNode<?>> getStoredEntityNodesFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredChildNodesWithHeader(NodeHeaderCatalogue.ENTITY);
  }

  @Override
  public boolean tableNodeContainsEntityNodeWithGivenId(final IMutableNode<?> tableNode, final String id) {
    return tableNodeContainsEntityNodeWhoseFieldAtGivenIndexHasGivenHeader(tableNode, FieldIndexCatalogue.ID_INDEX, id);
  }

  @Override
  public boolean tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenValue(
    final IMutableNode<?> tableNode,
    final int valueIndex,
    final String value) {
    return tableNode.containsChildNodeThat(
      (final var a) -> {

        if (!a.hasHeader(NodeHeaderCatalogue.ENTITY)) {
          return false;
        }

        final var field = a.getStoredChildNodeAt1BasedIndex(valueIndex);
        return (field.hasHeader(value) || field.containsChildNodeWithHeader(value));
      });
  }

  @Override
  public boolean tableNodeContainsEntityNodeWhoseFieldAtGivenIndexHasGivenHeader(
    final IMutableNode<?> tableNode,
    final int valueIndex,
    final String header) {
    return //
    tableNode.containsChildNodeThat(
      a -> a.hasHeader(NodeHeaderCatalogue.ENTITY)
      && a.getStoredChildNodeAt1BasedIndex(valueIndex).hasHeader(header));
  }
}
