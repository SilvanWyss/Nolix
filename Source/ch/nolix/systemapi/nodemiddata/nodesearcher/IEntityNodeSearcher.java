/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.nodemiddata.nodesearcher;

import ch.nolix.baseapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 */
public interface IEntityNodeSearcher {
  /**
   * @param entityNode
   * @return the id from the given entityNode
   */
  String getIdFromEntityNode(IMutableNode<?> entityNode);

  /**
   * @param entityNode
   * @return the save stamp from the given entityNode
   */
  String getSaveStampFromEntityNode(IMutableNode<?> entityNode);

  /**
   * @param entityNode
   * @return the id node from the given entityNode
   */
  IMutableNode<?> getStoredIdNodeFromEntityNode(IMutableNode<?> entityNode);

  /**
   * @param entityNode
   * @return the save stamp node from the given entityNode
   */
  IMutableNode<?> getStoredSaveStampNodeFromEntityNode(IMutableNode<?> entityNode);
}
