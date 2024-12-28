package ch.nolix.systemapi.noderawschemaapi.nodeexaminerapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public interface IEntityNodeExaminer {

  /**
   * @param entityNode
   * @param param1BasedColumnIndex
   * @return true if the field node of the given entity node at the given
   *         param1BasedColumnIndex is empty, false otherwise.
   */
  boolean fieldNodeOfEntityNodeAt1BasedColumnIndexIsEmpty(IMutableNode<?> entityNode, int param1BasedColumnIndex);
}
