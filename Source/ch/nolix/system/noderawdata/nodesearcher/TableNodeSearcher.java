package ch.nolix.system.noderawdata.nodesearcher;

import java.util.Optional;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.structure.SubNodeHeaderCatalogue;
import ch.nolix.system.noderawdata.tabledefinition.FieldIndexCatalogue;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.ITableNodeSearcher;

public final class TableNodeSearcher implements ITableNodeSearcher {

  @Override
  public Optional<? extends IMutableNode<?>> getOptionalStoredEntityNodeFromTableNode(
    final IMutableNode<?> tableNode,
    final String id) {
    return tableNode.getOptionalStoredFirstChildNodeThat(
      a -> a.hasHeader(SubNodeHeaderCatalogue.ENTITY)
      && a.getStoredChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).hasHeader(id));
  }

  @Override
  public IMutableNode<?> getStoredEntityNodeFromTableNode(final IMutableNode<?> tableNode, final String id) {
    return tableNode.getStoredFirstChildNodeThat(
      a -> a.hasHeader(SubNodeHeaderCatalogue.ENTITY)
      && a.getStoredChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).hasHeader(id));
  }

  @Override
  public IContainer<? extends IMutableNode<?>> getStoredEntityNodesFromTableNode(final IMutableNode<?> tableNode) {
    return tableNode.getStoredChildNodesWithHeader(SubNodeHeaderCatalogue.ENTITY);
  }

  @Override
  public IMutableNode<?> removeAndGetRefEntityNodeFromTableNode(IMutableNode<?> tableNode, String id) {
    return tableNode.removeAndGetStoredFirstChildNodeThat(
      a -> a.hasHeader(SubNodeHeaderCatalogue.ENTITY)
      && a.getStoredChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).hasHeader(id));
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

        if (!a.hasHeader(SubNodeHeaderCatalogue.ENTITY)) {
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
      a -> a.hasHeader(SubNodeHeaderCatalogue.ENTITY)
      && a.getStoredChildNodeAt1BasedIndex(valueIndex).hasHeader(header));
  }

  @Override
  public boolean tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenHeaderIgnoringGivenEntities(
    final IMutableNode<?> tableNode,
    final int index,
    final String header,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    tableNode.containsChildNodeThat(
      a -> a.hasHeader(SubNodeHeaderCatalogue.ENTITY)
      && entitiesToIgnoreIds.containsNone(a.getStoredChildNodeAt1BasedIndex(FieldIndexCatalogue.ID_INDEX).getHeader())
      && a.getStoredChildNodeAt1BasedIndex(index).hasHeader(header));
  }
}
