/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.nodesearcher;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IEntityNodeSearcher;

/**
 * @author Silvan Wyss
 */
public final class EntityNodeSearcher implements IEntityNodeSearcher {
  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredFieldNodeFromEntityNodeAtOneBasedColumnIndex(
    final IMutableNode<?> entityNode,
    final int oneBasedColumnIndex) {
    return entityNode.getStoredChildNodeAtOneBasedIndex(oneBasedColumnIndex);
  }
}
