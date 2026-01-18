package ch.nolix.system.nodemiddata.nodeexaminer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.nodemiddata.nodeexaminer.ITableNodeExaminer;
import ch.nolix.systemapi.nodemidschema.databasestructure.FieldIndexCatalog;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;

/**
 * @author Silvan Wyss
 */
public final class TableNodeExaminer implements ITableNodeExaminer {
  @Override
  public boolean tableNodeContainsEntityNodeWithFieldAtGivenOneBasedIndexWithGivenValueIgnoringGivenEntities(
    final IMutableNode<?> tableNode,
    final int oneBasedIndex,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    tableNode.containsChildNodeThat(
      a -> a.hasHeader(NodeHeaderCatalog.ENTITY)
      && a.getStoredChildNodeAtOneBasedIndex(oneBasedIndex).hasHeader(value)
      && !entitiesToIgnoreIds
        .containsEqualing(a.getStoredChildNodeAtOneBasedIndex(FieldIndexCatalog.ID_INDEX).getHeader()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean tableNodeContainsEntityNodeWithGivenId(final IMutableNode<?> tableNode, final String id) {
    return tableNodeContainsEntityNodeWhoseFieldAtGivenIndexHasGivenHeader(tableNode, FieldIndexCatalog.ID_INDEX, id);
  }

  /**
   * {@inheritDoc}
   */
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

        final var field = a.getStoredChildNodeAtOneBasedIndex(valueIndex);
        return (field.hasHeader(value) || field.containsChildNodeWithHeader(value));
      });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean tableNodeContainsEntityNodeWhoseFieldAtGivenIndexHasGivenHeader(
    final IMutableNode<?> tableNode,
    final int valueIndex,
    final String header) {
    return //
    tableNode.containsChildNodeThat(
      a -> a.hasHeader(NodeHeaderCatalog.ENTITY)
      && a.getStoredChildNodeAtOneBasedIndex(valueIndex).hasHeader(header));
  }
}
