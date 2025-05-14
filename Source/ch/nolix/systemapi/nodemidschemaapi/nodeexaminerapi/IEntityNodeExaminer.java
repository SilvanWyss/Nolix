package ch.nolix.systemapi.nodemidschemaapi.nodeexaminerapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public interface IEntityNodeExaminer {

  /**
   * @param entityNode
   * @param oneBasedColumnIndex
   * @return true if the field node of the given entity node at the given
   *         oneBasedColumnIndex is empty, false otherwise.
   */
  boolean fieldNodeOfEntityNodeAtOneBasedColumnIndexIsEmpty(IMutableNode<?> entityNode, int oneBasedColumnIndex);
}
