package ch.nolix.system.noderawdata.nodeexaminer;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.noderawdataapi.nodeexaminerapi.ITableNodeExaminer;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.FieldIndexCatalog;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;

public final class TableNodeExaminer implements ITableNodeExaminer {

  @Override
  public boolean tableNodeContainsEntityNodeWithFieldAtGiven1BasedIndexWithGivenValueIgnoringGivenEntities(
    final IMutableNode<?> tableNode,
    final int param1BasedIndex,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    tableNode.containsChildNodeThat(
      a -> a.hasHeader(NodeHeaderCatalog.ENTITY)
      && a.getStoredChildNodeAt1BasedIndex(param1BasedIndex).hasHeader(value)
      && !entitiesToIgnoreIds
        .containsEqualing(a.getStoredChildNodeAt1BasedIndex(FieldIndexCatalog.ID_INDEX).getHeader()));
  }

  @Override
  public boolean tableNodeContainsEntityNodeWithGivenId(final IMutableNode<?> tableNode, final String id) {
    return tableNodeContainsEntityNodeWhoseFieldAtGivenIndexHasGivenHeader(tableNode, FieldIndexCatalog.ID_INDEX, id);
  }

  @Override
  public boolean tableNodeContainsEntityNodeWhoseFieldAtGivenIndexContainsGivenValue(
    final IMutableNode<?> tableNode,
    final int valueIndex,
    final String value) {
    return tableNode.containsChildNodeThat(
      (final var a) -> {

        if (!a.hasHeader(NodeHeaderCatalog.ENTITY)) {
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
      a -> a.hasHeader(NodeHeaderCatalog.ENTITY)
      && a.getStoredChildNodeAt1BasedIndex(valueIndex).hasHeader(header));
  }
}
