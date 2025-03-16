package ch.nolix.systemapi.nodemiddataapi.nodeeditorapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

/**
 * @author Silvan Wyss
 * @version 2024-12-21
 */
public interface ITableNodeEditor {

  /**
   * Removes the first entity node with the given id from the given tableNode.
   * 
   * @param tableNode
   * @param id
   * @return the first entity node with the given id from the given tableNode.
   * @throws RuntimeException if the given tableNode is null.
   * @throws RuntimeException if the given tableNode does not contain an entity
   *                          node with the given id.
   */
  IMutableNode<?> removeAndGetStoredEntityNodeById(IMutableNode<?> tableNode, String id);
}
