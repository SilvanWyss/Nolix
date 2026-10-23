/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.nodeexaminer;

import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 */
public interface IEntityNodeExaminer {
  /**
   * @param entityNode
   * @param saveStamp
   * @return true if the given entityNode has the given saveStamp, false otherwise
   */
  boolean entityNodeHasSaveStamp(IMutableNode<?> entityNode, String saveStamp);
}
