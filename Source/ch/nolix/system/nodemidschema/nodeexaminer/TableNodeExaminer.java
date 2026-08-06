/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.nodeexaminer;

import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.system.nodemidschema.nodesearcher.TableNodeSearcher;
import ch.nolix.systemapi.nodemidschema.nodeexaminer.ITableNodeExaminer;

/**
 * @author Silvan Wyss
 */
public final class TableNodeExaminer implements ITableNodeExaminer {
  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final EntityNodeExaminer ENTITY_NODE_EXAMINER = new EntityNodeExaminer();

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
