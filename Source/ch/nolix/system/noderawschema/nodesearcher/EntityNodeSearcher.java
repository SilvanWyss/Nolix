package ch.nolix.system.noderawschema.nodesearcher;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IEntityNodeSearcher;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public final class EntityNodeSearcher implements IEntityNodeSearcher {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean fieldNodeOfEntityNodeAt1BasedColumnIndexIsEmpty(
    final IMutableNode<?> entityNode,
    final int param1BasedColumnIndex) {

    final var fieldNode = getStoredFieldNodeFromEntityNodeAt1BasedColumnIndex(entityNode, param1BasedColumnIndex);

    return fieldNode.isBlank();
  }

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
