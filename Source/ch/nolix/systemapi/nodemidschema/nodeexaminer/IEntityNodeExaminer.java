/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemidschema.nodeexaminer;

import ch.nolix.coreapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
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
