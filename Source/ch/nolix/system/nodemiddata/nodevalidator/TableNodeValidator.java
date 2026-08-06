/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.nodevalidator;

import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.nodemiddata.nodeexaminer.TableNodeExaminer;
import ch.nolix.systemapi.nodemiddata.nodevalidator.ITableNodeValidator;

/**
 * @author Silvan Wyss
 */
public final class TableNodeValidator implements ITableNodeValidator {
  private static final TableNodeExaminer TABLE_NODE_EXAMINER = new TableNodeExaminer();

  /**
   * {@inheritDoc}n
   */
  @Override
  public void assertTableNodeContainsEntityWithId(final IMutableNode<?> tableNode, final String entityId) {
    if (!TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWithGivenId(tableNode, entityId)) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        tableNode,
        "table node",
        "does not contain an entity with the id '" + entityId + "'");
    }
  }

  /**
   * {@inheritDoc}n
   */
  @Override
  public void assertTableNodeDoesNotContainEntityWithId(final IMutableNode<?> tableNode, final String entityId) {
    if (TABLE_NODE_EXAMINER.tableNodeContainsEntityNodeWithGivenId(tableNode, entityId)) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        tableNode,
        "table node",
        "contains an entity with the id '" + entityId + "'");
    }
  }
}
