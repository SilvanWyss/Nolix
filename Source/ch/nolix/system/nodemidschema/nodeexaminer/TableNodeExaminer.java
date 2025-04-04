package ch.nolix.system.nodemidschema.nodeexaminer;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodemidschema.nodesearcher.TableNodeSearcher;
import ch.nolix.systemapi.nodemidschemaapi.nodeexaminerapi.IEntityNodeExaminer;
import ch.nolix.systemapi.nodemidschemaapi.nodeexaminerapi.ITableNodeExaminer;
import ch.nolix.systemapi.nodemidschemaapi.nodesearcherapi.ITableNodeSearcher;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public final class TableNodeExaminer implements ITableNodeExaminer {

  private static final ITableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final IEntityNodeExaminer ENTITY_NODE_EXAMINER = new EntityNodeExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean columnOfTableNodeIsEmptyByColumnName(final IMutableNode<?> tableNode, final String columnName) {

    final var local1BasedColumnIndex = TABLE_NODE_SEARCHER.get1BasedIndexOfColumnInTableNodeByColumnName(tableNode,
      columnName);
    final var entityNodes = TABLE_NODE_SEARCHER.getStoredEntityNodesFromTableNode(tableNode);

    for (final var e : entityNodes) {
      if (ENTITY_NODE_EXAMINER.fieldNodeOfEntityNodeAt1BasedColumnIndexIsEmpty(e, local1BasedColumnIndex)) {
        return false;
      }
    }

    return true;
  }
}
