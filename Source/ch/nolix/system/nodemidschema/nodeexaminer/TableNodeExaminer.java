package ch.nolix.system.nodemidschema.nodeexaminer;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.nodemidschema.nodesearcher.TableNodeSearcher;
import ch.nolix.systemapi.nodemidschema.nodeexaminer.IEntityNodeExaminer;
import ch.nolix.systemapi.nodemidschema.nodeexaminer.ITableNodeExaminer;
import ch.nolix.systemapi.nodemidschema.nodesearcher.ITableNodeSearcher;

/**
 * @author Silvan Wyss
 */
public final class TableNodeExaminer implements ITableNodeExaminer {
  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final IEntityNodeExaminer ENTITY_NODE_EXAMINER = new EntityNodeExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean columnOfTableNodeIsEmptyByColumnName(final IMutableNode<?> tableNode, final String columnName) {
    final var localOneBasedColumnIndex = TABLE_NODE_SEARCHER.getOneBasedIndexOfColumnInTableNodeByColumnName(tableNode,
      columnName);
    final var entityNodes = TABLE_NODE_SEARCHER.getStoredEntityNodesFromTableNode(tableNode);

    for (final var e : entityNodes) {
      if (ENTITY_NODE_EXAMINER.fieldNodeOfEntityNodeAtOneBasedColumnIndexIsEmpty(e, localOneBasedColumnIndex)) {
        return false;
      }
    }

    return true;
  }
}
