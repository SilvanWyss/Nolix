package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public interface IEntityNodeSearcher {

  /**
   * @param entityNode
   * @param param1BasedColumnIndex
   * @return true if the field node of the given entity node at the given
   *         param1BasedColumnIndex is empty, false otherwise.
   */
  boolean fieldNodeOfEntityNodeAt1BasedColumnIndexIsEmpty(IMutableNode<?> entityNode, int param1BasedColumnIndex);

  /**
   * @param entityNode
   * @param param1BasedColumnIndex
   * @return the field node of the given entityNode at the given
   *         param1BasedColumnIndex.
   * @throws RuntimeException if given entityNode does not contain a field node
   *                          with the given param1BasedColumnIndex.
   */
  IMutableNode<?> getStoredFieldNodeFromEntityNodeAt1BasedColumnIndex(
    IMutableNode<?> entityNode,
    int param1BasedColumnIndex);
}
