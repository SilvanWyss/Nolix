package ch.nolix.systemapi.nodemiddata.nodevalidator;

import ch.nolix.coreapi.document.node.IMutableNode;

/**
 * @author Silvan Wyss
 * @version 2025-02-23
 */
public interface ITableNodeValidator {

  /**
   * @param tableNode
   * @param entityId
   * @throws RuntimeException if the given tableNode does not contain an entity
   *                          with the given entityId.
   */
  void assertTableNodeContainsEntityWithId(IMutableNode<?> tableNode, String entityId);

  /**
   * @param tableNode
   * @param entityId
   * @throws RuntimeException if the given tableNode contains an entity with the
   *                          given entityId.
   */
  void assertTableNodeDoesNotContainEntityWithId(IMutableNode<?> tableNode, String entityId);
}
