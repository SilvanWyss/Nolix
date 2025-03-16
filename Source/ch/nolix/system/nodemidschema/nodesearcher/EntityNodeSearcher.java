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
  public IMutableNode<?> getStoredFieldNodeFromEntityNodeAt1BasedColumnIndex(
    final IMutableNode<?> entityNode,
    final int param1BasedColumnIndex) {
    return entityNode.getStoredChildNodeAt1BasedIndex(param1BasedColumnIndex);
  }
}
