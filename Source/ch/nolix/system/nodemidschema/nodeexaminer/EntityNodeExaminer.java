/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.nodeexaminer;

import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.system.nodemidschema.nodesearcher.EntityNodeSearcher;
import ch.nolix.systemapi.nodemidschema.nodeexaminer.IEntityNodeExaminer;

/**
 * @author Silvan Wyss
 */
public final class EntityNodeExaminer implements IEntityNodeExaminer {
  private static final EntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

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
