package ch.nolix.system.nodemidschema.nodesearcher;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.nodemidschemaapi.nodesearcherapi.IEntityNodeSearcher;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
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
