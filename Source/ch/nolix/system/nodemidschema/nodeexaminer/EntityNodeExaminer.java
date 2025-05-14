package ch.nolix.system.nodemidschema.nodeexaminer;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodemidschema.nodesearcher.EntityNodeSearcher;
import ch.nolix.systemapi.nodemidschemaapi.nodeexaminerapi.IEntityNodeExaminer;
import ch.nolix.systemapi.nodemidschemaapi.nodesearcherapi.IEntityNodeSearcher;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public final class EntityNodeExaminer implements IEntityNodeExaminer {

  private static final IEntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean fieldNodeOfEntityNodeAtOneBasedColumnIndexIsEmpty(
    final IMutableNode<?> entityNode,
    final int oneBasedColumnIndex) {

    final var fieldNode = ENTITY_NODE_SEARCHER.getStoredFieldNodeFromEntityNodeAtOneBasedColumnIndex(entityNode,
      oneBasedColumnIndex);

    return fieldNode.isBlank();
  }
}
