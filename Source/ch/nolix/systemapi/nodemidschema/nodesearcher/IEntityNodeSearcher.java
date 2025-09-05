package ch.nolix.systemapi.nodemidschema.nodesearcher;

import ch.nolix.coreapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 * @version 2024-12-25
 */
public interface IEntityNodeSearcher {
  /**
   * @param entityNode
   * @param oneBasedColumnIndex
   * @return the field node of the given entityNode at the given
   *         oneBasedColumnIndex.
   * @throws RuntimeException if given entityNode does not contain a field node
   *                          with the given oneBasedColumnIndex.
   */
  IMutableNode<?> getStoredFieldNodeFromEntityNodeAtOneBasedColumnIndex(
    IMutableNode<?> entityNode,
    int oneBasedColumnIndex);
}
